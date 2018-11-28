import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.MRJobConfig;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

public class SmallSplit extends Configured implements Tool {
    //logFactory.getLog(Class)以 你传入的Class的类型字符串作为日志的“标题”
    private static final Log LOG = LogFactory.getLog( SmallSplit.class);
    private static final long ONE_MB = 1024 * 1024L;
 
    static class TextFileMapper extends Mapper<LongWritable , Text, Text, Text> {
 
        @Override
        protected void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            Configuration configuration = context.getConfiguration();
            LOG.warn("#######################" + configuration.get(MRJobConfig.MAP_INPUT_FILE));
            Text filenameKey = new Text(configuration.get(MRJobConfig.MAP_INPUT_FILE));
            context.write(filenameKey, value);
        }
    }
 
    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new SmallSplit(), args);
        System.exit(exitCode);
    }
 
    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = new Configuration(getConf());
        conf.set("mapreduce.input.fileinputformat.split.maxsize", String.valueOf(ONE_MB * 32));
        Job job = Job.getInstance(conf);
        FileInputFormat.setInputPaths(job, args[0]);
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.setJarByClass( SmallSplit.class);
        job.setInputFormatClass(CombineTextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setMapperClass(TextFileMapper.class);
        return job.waitForCompletion(true) ? 0 : 1;
    }
}