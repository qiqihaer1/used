package com.keduox;


import com.keduox.entity.UserAction01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserActionUtil {
    private static UserActionUtil userActionUtil;
    //全局变量，使内容不变
    private int user_id;
    private String session_id;


    public UserActionUtil() {
    }

    public static UserActionUtil getInstance(){
        userActionUtil= new UserActionUtil();
        return userActionUtil;
    }

    public  List<String> getUserAction(Date date1) {
        Random random=new Random();
        //设置全局变量
        //全局变量用户id
        user_id = random.nextInt(100)+1;
        //全局变量UUID防止循环中改变,并删除uuid中的"-"
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        session_id = str.replaceAll("-","");
        List<String> list=new ArrayList();

        //将传入的时间date进行转换，date为日期
        SimpleDateFormat date2 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat date3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=date2.format(date1);
        //点击时间action_time
        String action_time=date3.format(date1);

        UserAction01 user=null;
            for (int i=0;i<(random.nextInt(10)+1);i++){
                //页数id
                int page_id = random.nextInt(100)+1;
                //用户行为
                String[] actions= new String[]{"search","click","order","pay"};
                //关键字search_keyword
                String[] search_keywords=new String[]{"java","spark","hive","hbase","spring","hibernate","html","css","javascript","ajax","jquery"};
                String search_keyword=search_keywords[random.nextInt(search_keywords.length)];
                //点击类别click_category_id
                int click_category_id = random.nextInt(5)+1;
                //点击产品click_product_id
                int click_product_id = random.nextInt(20)+1;
                //订单类别
                int order_category_ids = random.nextInt(2)+1;
                //订单产品
                int order_product_ids =random.nextInt(20)+1;
                //支付类别
                int pay_category_id = random.nextInt(3);
                //支付产品
                int pay_product_id = random.nextInt(20)+1;
                //省份id
                int city_id = random.nextInt(31)+1;

                //用户行为判断
                String action=actions[random.nextInt(actions.length)];
                if("search".equals(action.trim())){
                    user=new UserAction01(date,user_id,session_id,page_id,
                            action_time,search_keyword,
                            -1,-1,
                            -1,-1,
                            -1,-1,
                            city_id);
                }else if("click".equals(action.trim())){
                    user=new UserAction01(date,user_id,session_id,page_id,
                            action_time,null,
                            click_category_id,click_product_id,
                            -1,-1,
                            -1,-1,
                            city_id);
                }else if("order".equals(action.trim())){
                    user=new UserAction01(date,user_id,session_id,page_id,
                            action_time,null,
                            -1,-1,
                            order_category_ids,order_product_ids,
                            -1,-1,
                            city_id);
                }else if("pay".equals(action.trim())){
                    user=new UserAction01(date,user_id,session_id,page_id,
                            action_time,null,
                            -1,-1,
                            -1,-1,
                            pay_category_id,pay_product_id,
                            city_id);
                }
//              System.out.println(user);
                list.add(user.toString());

            }
//        System.out.println(list);
        return list;
    }


    public void saveFile(String str){
        //将user存储到本地文件，首先打开目标文件
        File file=new File("d:/userAction.txt");
        //如果文件不存在，就创建
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if (!file.exists()){
            try {
                file.createNewFile();
                System.out.println("文件创建成功！");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fos=null;
        try {
            //打开输出流,不覆盖，使用追加数据
            fos=new FileOutputStream(file,true);
            //将字符串转换为byte数据
            byte[] b=str.toString().getBytes();
            //输出内容
            fos.write(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭流
            if (fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
