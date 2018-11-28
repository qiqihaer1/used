package com.keduox.map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 泛型四个参数说明
 * 前两个为mapreduce传递给我们的，一般情况下固定写成LongWritable,Text（相当于String）
 * 后签个为map处理后的输出类型，根据需求而定
 */
public class LocationMap extends Mapper<LongWritable,Text,Text,IntWritable> {
    /**
     * 这个map方法，会由mapreduce程序自己来调用，用户不需要调用
     * 我们只需要写好
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //value代表一行的数据
        //将一行的数据拆分成一个个的单词
        String[] dataArray = value.toString().split("\t");
        //数据清洗
        if (dataArray.length==3){
            context.write(new Text(dataArray[1]),new IntWritable(Integer.parseInt(dataArray[2])));
        }
    }
}
