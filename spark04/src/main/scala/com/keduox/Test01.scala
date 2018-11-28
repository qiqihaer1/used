package com.keduox

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}


object Test01 {
  def main(args: Array[String]): Unit = {
    //简化日志
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    // 必须用多个分区!!
    val conf = new SparkConf().setMaster("local[2]").setAppName("sqlDS")
    //第二个参数是流处理时间间隔
    val streamingContext = new StreamingContext(conf,Seconds(2))

    //设置数据来源,DStream来源于TCP，设置端口和地址
    val ms = streamingContext.socketTextStream("salt",9990)
    //从文件系统读取数据流必须时间同步
//    val ms = streamingContext.textFileStream("d:/spark_eg1")
    //处理数据
    val unit: DStream[(String, Int)] = ms.flatMap(_.split(" ")).map(data=>(data,1)).reduceByKey(_+_)
    unit.print()
    //对于DS的操作，我们需要单独的启动
    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
