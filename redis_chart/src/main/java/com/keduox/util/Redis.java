package com.keduox.util;

import redis.clients.jedis.Jedis;

import java.util.*;

public class Redis {
private final static Redis redis = new Redis();
//    public static void main(String[] args) {
//        List<Map> webRedis = getWebRedis();
//        System.out.println(webRedis);
//    }


    public Redis() {
    }

    public static Redis getInstance(){
        return redis;
    }

    public  List<Map> getProvinceRedis(){

        List list = new ArrayList();
        //连接redis
        Jedis jedis = new Jedis("localhost",6379);
        //获取redis里的数据
        Set<String> keys = jedis.keys("*");
        for (String str:keys){
            //通过取首字母判断是否为中文
            char c = str.charAt(0);
            if(isChinese(c)){
//              System.out.println("key:"+str);
                //在内部建立Map,每次放入List中的都是新的Map
                Map<String,String> map = new HashMap<>();
                map.put("name",str);
                map.put("value",jedis.get(str));
                list.add(map);
            }
        }
        System.out.println(list);
        //关闭连接
        jedis.close();
        return list;
    }

    public  List<Map> getWebRedis(){
        List<Map> list = new ArrayList<>();

        String[] arraydata=new String[]{"ie","chrome","firefox","safari","opera"};
        //连接redis
        Jedis jedis = new Jedis("localhost",6379);
        //获取redis里的数据
        for (int i=0;i<arraydata.length;i++){
            Map<String,String> map = new HashMap<>();
            map.put("name",arraydata[i]);
            map.put("value",jedis.get(arraydata[i]));
            list.add(map);
        }
        System.out.println(list);
        //关闭连接
        jedis.close();
        return list;
    }

    /**
     * 使用正则表达式来判断key是否为中文
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {

        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS

                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS

                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A

                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION

                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION

                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {

            return true;

        }

        return false;

    }

}
