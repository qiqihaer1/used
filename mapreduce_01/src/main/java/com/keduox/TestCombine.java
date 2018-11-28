package com.keduox;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.ReflectionUtils;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;


public class TestCombine extends Configured implements Tool {

    //map,传出value,value
    private static class ProvinceMapper extends Mapper<Object, Text, Text, Text> {
        @Override
        protected void map(Object key, Text value, Mapper.Context context) throws IOException, InterruptedException {
            System.out.println("value : " + value + " Context " + context);
            context.write(value, value);
        }
    }
    //reduce
    private static class ProvinceReducer extends Reducer<Text, Text, Text, Text> {
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            for (Text va : values) {
                System.out.println("reduce " + key);
                context.write(key, key);
            }
        }
    }

    // CombineFileInputFormat<K, V>
    public static class CombineSequenceFileInputFormat<K, V> extends CombineFileInputFormat<K, V> {

        @SuppressWarnings({ "unchecked", "rawtypes" })//批注允许您选择性地取消特定代码段（即，类或方法）中的警告
        @Override
        public RecordReader<K, V> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException {
            return new CombineFileRecordReader((CombineFileSplit)split, context, CombineLineRecordReader.class);
        }
    }

    //RecordReader,读取每个小文件内容
    public static class CombineLineRecordReader<K, V> extends RecordReader<K, V> {
        private CombineFileSplit split;
        private TaskAttemptContext context;
        private int index;
        private RecordReader<K, V> rr;

        @SuppressWarnings("unchecked")
        public CombineLineRecordReader(CombineFileSplit split, TaskAttemptContext context, Integer index) throws IOException, InterruptedException {
            this.index = index;
            this.split = (CombineFileSplit) split;
            this.context = context;

            this.rr = (RecordReader<K, V>) ReflectionUtils.newInstance(LineRecordReader.class, context.getConfiguration());
        }

        @SuppressWarnings("unchecked")
        @Override
        public void initialize(InputSplit curSplit, TaskAttemptContext curContext) throws IOException, InterruptedException {
            this.split = (CombineFileSplit) curSplit;
            this.context = curContext;

            if (null == rr) {
                rr = ReflectionUtils.newInstance(SequenceFileRecordReader.class, context.getConfiguration());
            }

            FileSplit fileSplit = new FileSplit(this.split.getPath(index),
                    this.split.getOffset(index), this.split.getLength(index),
                    this.split.getLocations());

            this.rr.initialize(fileSplit, this.context);
        }

        @Override
        public float getProgress() throws IOException, InterruptedException {
            return rr.getProgress();
        }

        @Override
        public void close() throws IOException {
            if (null != rr) {
                rr.close();
                rr = null;
            }
        }

        @Override
        public K getCurrentKey()
                throws IOException, InterruptedException {
            return rr.getCurrentKey();
        }

        @Override
        public V getCurrentValue()
                throws IOException, InterruptedException {
            return rr.getCurrentValue();
        }

        @Override
        public boolean nextKeyValue() throws IOException, InterruptedException {
            return rr.nextKeyValue();
        }
    }


    public int run(String[] args) throws Exception {
        Configuration conf = new Configuration();

        Job job = new Job(conf);
        job.setJobName("TestCombine");
        job.setJarByClass(TestCombine.class);
        //设置map,reduce关联
        job.setMapperClass(ProvinceMapper.class);
        job.setReducerClass(ProvinceReducer.class);
        //设置输入类型
        job.setInputFormatClass(CombineSequenceFileInputFormat.class);
        //设置输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        String inpath = "/hello.txt";
        String outpath = "/hdfs_data";
        Path p = new Path(outpath);

        FileSystem fs = FileSystem.get(conf);
        if (fs.exists(p)){
            fs.delete(p);
        }
        //告诉job从哪里拿数据-hdfs
        FileInputFormat.addInputPaths(job, inpath);
        //将结果输出到哪里—hdfs
        FileOutputFormat.setOutputPath(job, p);
        ////启动job,成功返回0，失败返回1
        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int ret = ToolRunner.run(new TestCombine(), args);
        System.exit(ret);
    }
}
