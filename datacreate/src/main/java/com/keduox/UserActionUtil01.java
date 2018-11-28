package com.keduox;


import com.keduox.entity.UserAction01;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserActionUtil01 {
    private String MAKE = "\t";//分隔符
    private String date;//访问时间（yyyy-MM-dd）
    private int userId;//用户ID
    private String sessionId;//用户会话ID

    Random random = new Random();
    //点击类别的初始值
    private final String[] CLICK_ARRAY = {"search", "click", "order", "pay"};
    //搜索关键值的初始值
    private final String[] SEARCH_ARRAY = {"java", "spark", "hive", "hbase", "spring", "hibernate", "html", "css", "javascript", "ajax", "jquery"};

    /**
     * 该方法返回一个用户的访问记录
     *
     * @return
     */
    public List<String> getUserListData(String date) {
        this.date = date;
        userId = random.nextInt(20) + 1;
        sessionId = UUID.randomUUID().toString().replaceAll("-", "");
        List<String> resultData = new ArrayList<String>();
        for (int i = 0; i < random.nextInt(40) + 1; i++) {
            int pageId;//页面ID
            String actionTime = null;//点击具体时间
            String searchKeyword = null;//查询关键字
            int clickCategoryId = -1;//点击类别
            int clickProductId = -1;//点击产品
            int orderCategoryIds = -1;//订单类别
            int orderProductIds = -1;//订单产品
            int payCategoryIds = -1;//支付类别
            int payProductIds = -1;//支付产品
            int cityId = -1;//访问地区
            pageId = random.nextInt(20) + 1;
            actionTime = getDateFormat();
            if ("search".equals(CLICK_ARRAY[random.nextInt(CLICK_ARRAY.length)])) {
                searchKeyword = SEARCH_ARRAY[random.nextInt(SEARCH_ARRAY.length)];
            } else if ("click".equals(CLICK_ARRAY[random.nextInt(CLICK_ARRAY.length)])) {
                //如果类别在后期分析的时候需要用到的话，那么该值应该根据商品id去获取相应的类型id
                //如果没有用到该字符的话，那么就可以随机生成一个值就可以了
                clickCategoryId = random.nextInt(10) + 1;
                clickProductId = random.nextInt(20) + 1;
            } else if ("order".equals(CLICK_ARRAY[random.nextInt(CLICK_ARRAY.length)])) {
                orderCategoryIds = random.nextInt(10) + 1;
                orderProductIds = random.nextInt(20) + 1;
            } else {
                payCategoryIds = random.nextInt(3) + 1;
                payProductIds = random.nextInt(20) + 1;
            }
            cityId = random.nextInt(31) + 1;
            StringBuffer sb = new StringBuffer();
            sb.append(this.date).append(MAKE).append(userId).append(MAKE).append(sessionId).append(MAKE).append(pageId).append(MAKE).append(actionTime).append(MAKE).append(searchKeyword).append(MAKE);
            sb.append(clickCategoryId).append(MAKE).append(clickProductId).append(MAKE).append(orderCategoryIds).append(MAKE).append(orderProductIds).append(MAKE).append(payCategoryIds).append(MAKE);
            sb.append(payProductIds).append(MAKE).append(cityId);
            resultData.add(sb.toString());
        }

        return resultData;
    }

    /**
     * 得到一个年月日 时分秒的时间字符串
     *
     * @return
     */
    private String getDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return simpleDateFormat.format(new Date());
    }


    public static void main(String[] args) {
        String date = "2018-09-26";
        UserActionUtil01 cdt = new UserActionUtil01();
        for (int i = 0; i < 1000; i++) {
            for (String data : cdt.getUserListData(date)) {
                cdt.saveFile(data);
            }
        }
    }

    public void saveFile(String data) {
        System.out.println(data);
        //File file = new File("E:/data.txt");
        FileWriter fileWriter = null;
        BufferedWriter bw = null;
        try {
            final File file = new File("D:/data.txt");
            fileWriter = new FileWriter(file, true);
            bw = new BufferedWriter(fileWriter);
            if (file.length() != 0) {
                bw.newLine();
            }
            bw.write(data);

            bw.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}