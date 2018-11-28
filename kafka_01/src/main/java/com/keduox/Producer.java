package com.keduox;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class Producer {

    public static void main(String[] args) {
//        Properties properties= new Properties();
//        //必须放入方法中才能使用其属性
//        properties.setProperty("bootstrap.servers","salt:9092");
//        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
//        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
//        Properties properties=kafkaUtil.getPropertiesByFilename(Constant.FILE_NAME);
        KafkaUtil kafkaUtil = KafkaUtil.newInstance();
        Properties properties=kafkaUtil.getPropertiesByFilename("producer");
        KafkaProducer<String,String> kafkaproducer=new KafkaProducer<>(properties);
        //序列化

        //发送消息
        for (int i=0;i<7;i++){
            //当我们向kafka生产数据时，有多种方式topic partition key value
            // 新版本数据是ProducerRecord，老版本数据是keyedMessage
            //topic value
            ProducerRecord<String,String> producerRecord=new ProducerRecord<String,String>("test","18168028913",i+"测试");
            //当某些数据要放到指定分区里
//            ProducerRecord<String,String> producerRecord1=new ProducerRecord<String,String>("test",1,"1",i+"");
            //防止两条数据重复却无法区分
            ProducerRecord<String,String> producerRecord2=new ProducerRecord<String,String>("test",1,new Long(1),"1",i+"");
            kafkaproducer.send(producerRecord);
//            kafkaproducer.send(producerRecord, new Callback() {
//                @Override
//                public void onCompletion(RecordMetadata metadata, Exception exception) {
//           //1.当Producer发送异常后，先将异常数据存入数据库(mysql)；
            //当Producer发送成功后(exception=null)，将数据库(mysql)对应的数据删除；
//                }
//            });
        }
        //关闭流
        kafkaproducer.close();
    }

}
