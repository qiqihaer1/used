package com.keduox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Run {
    public static void main(String[] args) {
//        IpUtil ipUtil = IpUtil.getInstance();
//        String ip=ipUtil.createIp();
//        System.out.println(ip);
        //获取数据
        UserActionUtil userActionUtil = UserActionUtil.getInstance();
        //当前时间
        Date date=new Date();

        //输出内容
        for (int i=0;i<50000;i++){
            for(Object str:userActionUtil.getUserAction(date)){
                System.out.println(str);
                userActionUtil.saveFile(str.toString());
            }
        }




    }
}
