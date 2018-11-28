package com.keduox;

import java.io.IOException;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

public class WholeFileInputFormat extends FileInputFormat<NullWritable, BytesWritable> {

    //继承自FileInputFormat,实现createRecordReader()方法
    @Override
    public RecordReader<NullWritable, BytesWritable> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        RecordReader<NullWritable, BytesWritable> recordReader = new WholeFileRecordReader();
        //调用WholeFileRecordReader的initialize()方法初始化，自定义文件名和文件地址
        recordReader.initialize(split, context);
        return recordReader;
    }
}