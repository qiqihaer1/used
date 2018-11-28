package com.keduox;

import com.keduox.entity.Client;
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
import java.text.SimpleDateFormat;
import java.util.*;

public class ClientUtil {
    public static void main(String[] args) {
        List list1=new ArrayList();
        List list2=new ArrayList();
        Map<String,String> map=null;
        IpUtil ipUtil = IpUtil.getInstance();
        //时间
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String day=sdf.format(date);
        //浏览器
        String[] browsers= new String[]{"chrome","firefox","ie","safari","opera"};

        Random random=new Random();
        Client client=null;
            for (int i=0;i<10;i++){

                String ip= ipUtil.createIp();
                String browser=browsers[random.nextInt(browsers.length-1)];
                map=new HashMap<String,String>();
                client=new Client(day,ip,browser);
                System.out.println(client);
                map.put("time",day);
                map.put("ip",ip);
                map.put("browser",browser);
                list2.add(map);
            }
        System.out.println(list2);
    }
}
