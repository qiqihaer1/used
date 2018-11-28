package com.keduox;

import java.io.IOException;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


//读取每个小文件内容的WholeFileRecordReader
//*输入的键值对类型，对小文件，每个文件对应一个InputSplit
public class WholeFileRecordReader extends RecordReader<NullWritable, BytesWritable> {

    private FileSplit fileSplit;
    private JobContext jobContext;
    private NullWritable currentKey = NullWritable.get();
    private BytesWritable currentValue;
    private boolean finishConverting = false;

    @Override
    public NullWritable getCurrentKey() throws IOException, InterruptedException {
        return currentKey;
    }

    //我们读取这个InputSplit实际上就是具有一个Block的整个文件的内容，将整个文件的内容读取到BytesWritable，也就是一个字节数组
    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return currentValue;
    }

    @Override
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        this.fileSplit = (FileSplit) split;
        this.jobContext = context;
        //设置map.input.file为文件名
        context.getConfiguration().set("map.input.file", fileSplit.getPath().getName());
    }

    //实现RecordReader接口，最核心的就是处理好迭代多行文本的内容的逻辑
    // 每次迭代通过调用nextKeyValue()方法来判断是否还有可读的文本行，直接设置当前的Key和Value
    // 分别在方法getCurrentKey()和getCurrentValue()中返回对应的值。
    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if (!finishConverting) {
            //输出值
            currentValue = new BytesWritable();
            int len = (int) fileSplit.getLength();
            //有长度的缓存
            byte[] content = new byte[len];
            Path file = fileSplit.getPath();
            FileSystem fs = file.getFileSystem(jobContext.getConfiguration());
            FSDataInputStream in = null;
            try {
                in = fs.open(file);
                //读取指定长度的流，如果读取的长度不够，就会抛出异常
                IOUtils.readFully(in, content, 0, len);
                //给currentValue赋值content
                currentValue.set(content, 0, len);
            } finally {
                if (in != null) {
                    IOUtils.closeStream(in);
                }
            }
            finishConverting = true;
            return true;
        }
        return false;
    }

    @Override
    public float getProgress() throws IOException {
        float progress = 0;
        if (finishConverting) {
            progress = 1;
        }
        return progress;
    }

    @Override
    public void close() throws IOException {
        // TODO Auto-generated method stub

    }
}
