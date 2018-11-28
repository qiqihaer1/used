package com.keduox;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;


import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class TestHdfs {
    public static void main(String[] args) {
        try {
            //连接HDFS
            Configuration conf = new Configuration();
            conf.set("dfs.replication","1");
            FileSystem fs = FileSystem.get(new URI( "hdfs://192.168.200.21:9000"),conf,"root");
            //读取某个文件
//            FSDataInputStream fdis = fs.open(new Path("/hello.txt"));
            //将文件存储到D盘
//            FileOutputStream fos = new FileOutputStream("D:/hello.txt");
//            IOUtils.copyBytes(fdis,fos, 4096);
            //关闭
//            fos.close();
//            fdis.close();
            //在HDFS中创建文件夹
//            fs.mkdirs(new Path("/keduox"));
            //在HDFS中删除文件夹
           // fs.delete(new Path("/hello.txt"),true);
            //创建新文件
            fs.createNewFile(new Path("/hahaha.txt"));
            //下载文件
//            fs.copyToLocalFile(false,new Path("/hahaha.txt"),new Path("D:/"),true);
            //上传文件
//            fs.copyFromLocalFile(new Path("d:/5555.txt"),new Path("/keduox"));
            //将数据追加到某个文件中
            InputStream is = new BufferedInputStream(new FileInputStream("D:/5555.txt"));
            FSDataOutputStream out = fs.append(new Path("/hahaha.txt"));
            IOUtils.copyBytes(is, out, 4096);
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
