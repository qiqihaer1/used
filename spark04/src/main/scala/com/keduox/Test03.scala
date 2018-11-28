package com.keduox

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Test03 {
  def main(args: Array[String]): Unit = {
    //简化日志
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
      // 必须用多个分区!!
      val conf = new SparkConf().setMaster("local[2]").setAppName("sql1")
      //第二个参数是流处理时间间隔
      val ssc = new StreamingContext(conf,Seconds(2))
    //拿到DStream
    val value: ReceiverInputDStream[String] = ssc.socketTextStream("192.168.200.21",9990)
    //操作,开启缓存，cache()将之前的算子缓存进
    value.flatMap(_.split(" ")).map((_,1)).cache().reduceByKey(_+_).print()
    //启动
    ssc.start()
    ssc.awaitTermination()
  }
}
