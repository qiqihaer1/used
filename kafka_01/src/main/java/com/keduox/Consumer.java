package com.keduox;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class Consumer {
    public static void main(String[] args) {
        Properties properties=new Properties();
        properties.setProperty("bootstrap.servers","salt:9092");
        //反序列化
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //设置GC的ID
        properties.put("group.id","haha");
        //修改为手动提交！！！
        properties.put("enable.auto.commit","false");
        //CG的定义
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        //订阅消息
//        kafkaConsumer.subscribe(Arrays.asList("test"));
        //订阅主题的分区消息
        TopicPartition p0 = new TopicPartition("test", 0);
        TopicPartition p1 = new TopicPartition("test", 1);
        kafkaConsumer.assign(Arrays.asList(p0,p1));
        //接收消息
        while (true){
            //一次拿100条
            ConsumerRecords<String,String> records=kafkaConsumer.poll(1000);
            //将内容放入mysql
            //正常情况下，将数据写入mysql的话，不管offset什么时候改变都可以，但写入失败时，kafka的高级API会把offset修改了，
            //我们这一次写入失败的数据将会存在丢失
            // 解决办法是：只有当写入成功后才提供offset的修改,将consumer默认的自动提交改为手动提交！
            for(ConsumerRecord<String,String> record: records){
                System.out.println(record.partition()+"|"+record.value());
            }
            kafkaConsumer.commitSync();
        }


    }
}
