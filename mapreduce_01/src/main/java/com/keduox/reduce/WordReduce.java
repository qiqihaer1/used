package com.keduox.reduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordReduce extends Reducer<Text,IntWritable,Text,LongWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //values是由mapreduce处理过程中，将相同的key进行合并后，传递给我们的数据
       long result=0L;
        for (IntWritable value:values){
            result+=value.get();
        }
        //发送出去
        context.write(key,new LongWritable(result));



    }
}
