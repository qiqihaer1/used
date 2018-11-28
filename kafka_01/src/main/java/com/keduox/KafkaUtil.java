package com.keduox;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class KafkaUtil {
    //添加常量，文件名后缀
    private final static String FILE_SUFFIX=".properties";
    //单例模式常量
    private final static KafkaUtil kafkaUtil=new KafkaUtil();

    public KafkaUtil() {

    }

    //单例模式，static
    public static KafkaUtil newInstance(){
//        KafkaUtil kafkaUtil=new KafkaUtil();
        return kafkaUtil;
    }

//    public static void main(String[] args) {
//        KafkaUtil kafkaUtil = KafkaUtil.newInstance();
//        Properties properties = kafkaUtil.getPropertiesByFilename("producer");
//        String property1 = properties.getProperty("bootstrap.servers");
//        String property2 = properties.getProperty("key.serializer");
//        String property3 = properties.getProperty("value.serializer");
//        System.out.println(property1);
//        System.out.println(property2);
//        System.out.println(property3);
//    }

    public Properties getPropertiesByFilename(String filename){
        Properties properties= new Properties();
        //输入流
        InputStream is=KafkaUtil.class.getClassLoader().getResourceAsStream(filename+FILE_SUFFIX);
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        //连接kafka
//        properties.setProperty("bootstrap.servers","salt:9092");
//        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
//        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        return properties;
    }

    public Properties CustomerInstance(String str){
        Properties properties=new Properties();
        properties.setProperty("bootstrap.servers","salt:9092");
        //反序列化
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //设置GC的ID
        properties.put("group.id",str);
        return properties;
    }

}
