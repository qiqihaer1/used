package com.keduox;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Reducer;

public class IdentityReducer<Text, BytesWritable> extends Reducer<Text, BytesWritable, Text, BytesWritable> {

    //将Map任务的输出原样写入到HDFS中
    @Override
    protected void reduce(Text key, Iterable<BytesWritable> values, Context context) throws IOException, InterruptedException {
        for (BytesWritable value : values) {
            context.write(key, value);
        }
    }
}
