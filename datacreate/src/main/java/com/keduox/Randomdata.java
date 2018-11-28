package com.keduox;

import com.keduox.entity.Message;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Randomdata {
    public static void main(String[] args) {
//        List list=new ArrayList();
        String[] arrayms=null;
        Message ms = null;

        String[] movienames= new String[]{"情圣","铁道飞虎","长城","酒国英雄之摆渡人","血战钢锯岭",
                "那年夏天你去了哪", "冰雪女皇之冬日魔","你好，疯子！","罗曼蒂克消亡史 "};
        String[] locations=new String[]{"成都","北京","上海","广州","重庆","福建","深圳","杭州","天津"};
        String[] prices=new String[]{"20","25","30"};
        Random random=new Random();



        //连接HDFS
        Configuration conf = new Configuration();
        conf.set("dfs.replication","1");
        try {
            FileSystem fs = FileSystem.get(new URI( "hdfs://192.168.200.100:9000"),conf);
            //创建新文件
            Path outputPath = new Path("/message.txt");
            if (!fs.exists(outputPath)) {
                fs.createNewFile(outputPath);
            }

            //创建文件的输出流
            FSDataOutputStream out =fs.append(new Path("/message.txt"));
            for (int i=0;i<500;i++){
                int n1=random.nextInt(movienames.length-1);
                int n2=random.nextInt(locations.length-1);
                int n3=random.nextInt(prices.length-1);
                ms=new Message(movienames[n1],locations[n2],prices[n3]);
                //创建文件的输入流
                ByteArrayInputStream bais=new ByteArrayInputStream(ms.toString().getBytes());
                //将输入流放入文件
                IOUtils.copyBytes(bais, out, 4096);
            }

            fs.close();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
