package com.keduox.job;

import com.keduox.map.WordMap;
import com.keduox.reduce.WordReduce;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordJob {
    public static void main(String[] args) {
        //创建Job
        FileSystem fs = null;
        try {
            Configuration configuration = new Configuration();
            //更改路径
            configuration.set("fs.defaultFS", "hdfs://192.168.200.21:9000");
            Job job = Job.getInstance(configuration);
            job.setJarByClass(WordJob.class);
            job.setJobName("word_job");
            //设置关联的东西
            job.setMapperClass(WordMap.class);
            job.setReducerClass(WordReduce.class);
            //设置输出类型
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(IntWritable.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(LongWritable.class);
            //告诉job从哪里拿数据-hdfs
            FileInputFormat.addInputPath(job, new Path("/hello.txt"));
            //判断输出目录是否存在
            fs = FileSystem.get(configuration);
            Path outputPath = new Path("/spark_out");
            if (fs.exists(outputPath)) {
                fs.delete(outputPath, true);
            }
            //将结果输出到哪里—hdfs
            FileOutputFormat.setOutputPath(job, new Path("/spark_out/"));
            //启动
            boolean flag = job.waitForCompletion(true);
            if (flag) {
                System.out.println("程序执行成功!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fs != null) {
                fs.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
