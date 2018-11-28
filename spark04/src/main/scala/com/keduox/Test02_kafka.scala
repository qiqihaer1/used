package com.keduox

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Test02_kafka {
  //简化日志
  Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
  def main(args: Array[String]): Unit = {

    // 必须用多个分区!!
    val conf = new SparkConf().setMaster("local[2]").setAppName("sql")
    //第二个参数是流处理时间间隔
    val ssc = new StreamingContext(conf,Seconds(2))
    //创建流数据DStream,从高级来源kafka中取数据，先导入JAR包
//    val value = ssc.socketTextStream("salt",9998)
//    value.print()
    /**
      * 参数当中，第一个参数需要我们加入ssc
      * 第2,3,4参数：zk的地址\group_id\topics,可以同时读取多个主题下的消息
      */
    //返回的类型是：(String, String) 我们kafka的消息分为两部分：key--->value
       val kafkaDs: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(ssc,"salt:2181","spark01",Map("mytopic"->3))
//       val kafkaDs: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(ssc, "salt:2181", "spark_id", Map("mytopic" -> 3))
      kafkaDs.print()
    //启动
    ssc.start()
    ssc.awaitTermination()

  }

}
