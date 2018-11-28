package com.keduox;

import com.keduox.entity.BankMessage;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TestUtil {

    public static void Util(){
        BankMessage bms = null;
        //产生随机数
        Random random=new Random();
        //名字
        String[] names= new String[]{"ben","anna","leo","zsan","mayun","hehe","hexie"};
        //时间
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String day=sdf.format(date);

        //为了刷新文件，每次循环都开关流！！！！！
        for (int i=0;i<56000;i++){
        //连接HDFS
        Configuration conf = new Configuration();
        conf.set("dfs.replication","3");
        try {
            FileSystem fs = FileSystem.get(new URI( "hdfs://192.168.200.100:9000"),conf);
            //创建新文件
            Path outputPath = new Path("/bank/"+day+"_customer_tx.txt");
            if (!fs.exists(outputPath)) {
                fs.createNewFile(outputPath);
            }
            //创建文件的输出流
            FSDataOutputStream out =fs.append(new Path("/bank/"+day+"_customer_tx.txt"));

                //名字
                int n1=random.nextInt(names.length-1);
                String name=names[n1]+random.nextInt(100);
                //消费
                int price=random.nextInt(90)+10;
                //放入自定义对象
                bms=new BankMessage(name,price,day);
                //创建文件的输入流
                ByteArrayInputStream bais=new ByteArrayInputStream(bms.toString().getBytes());

                //判断文件大小，大于10mb则不将数据放入文件
                //实时刷新文件大小，否则数据在内存中不会变化

                FileStatus status=fs.getFileStatus(outputPath);
                //获取文件大小
                if(status.getLen()>=10485760){
                    //创建新文件.success
                    fs.createNewFile(new Path("/bank/"+day+"_customer_tx.txt.success"));
                    System.out.println("文件大小是："+status.getLen()+",太大了");
                    fs.close();
                    return;
                }

                //将输入流放入文件
                IOUtils.copyBytes(bais, out, 4096);


            fs.close();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        }
    }

}