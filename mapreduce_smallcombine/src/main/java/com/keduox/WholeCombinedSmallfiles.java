package com.keduox;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;


public class WholeCombinedSmallfiles {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();
        //用来解释常用的Hadoop命令行选项，并根据需要，为Configuration对象设置相应的取值,可以替换为实现Tool接口
        //发布命令时输入的文件路径的数组
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2) {
            System.err.println("Usage: conbinesmallfiles <in> <out>");
            //异常退出状态码2
            System.exit(2);
        }

        Job job = new Job(conf, "combine smallfiles");
        //设置关联map和reduce
        job.setJarByClass(WholeCombinedSmallfiles.class);
        job.setMapperClass(WholeSmallfilesMapper.class);
        job.setReducerClass(IdentityReducer.class);
        //map输出、reduce输出类型设置
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(BytesWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(BytesWritable.class);
        //输入的是自定义类型，输出的是序列化类型
        job.setInputFormatClass(WholeFileInputFormat.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        //设置5个reduce，会有5个输出文件
        job.setNumReduceTasks(5);
        //告诉job执行文件在哪
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        //告诉job执行文件输出在哪
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        //启动job并返回0或1
        int exitFlag = job.waitForCompletion(true) ? 0 : 1;
        //waitForCompletion(true)成功则返回true->0，退出程序
        System.exit(exitFlag);
    }
//
// hadoop jar mapreduce_smallcombine-1.0-SNAPSHOT.jar com.keduox.WholeCombinedSmallfiles 小文件目录绝对路径 结果目录绝对路径

}
