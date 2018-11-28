package com.keduox.util;

import com.keduox.entity.Client;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

public class ClientUtil {
    private static ClientUtil clientUtil;

    public static ClientUtil getInstance(){
        clientUtil=new ClientUtil();
        return clientUtil;
    }

    public Client getMessage(){
        List list=new ArrayList();
        Map<String,String> map=new HashMap<String,String>();
        IpUtil ipUtil = IpUtil.getInstance();
        //时间
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String day=sdf.format(date);
        //浏览器
        String[] browsers= new String[]{"chrome","firefox","ie","safari","opera"};

        Random random=new Random();

        Logger logger = Logger.getLogger("getMessage");
        //ip数据
        String ip= ipUtil.createIp();
        //浏览器数据
        String browser=browsers[random.nextInt(browsers.length)];
        //获取组成数据
        Client client=new Client(day,ip,browser);
//            map.put("time",day);
//            map.put("ip",ip);
//            map.put("browser",browser);
//            //获取集合数据
//            list.add(map);
        return client;
    }

}
