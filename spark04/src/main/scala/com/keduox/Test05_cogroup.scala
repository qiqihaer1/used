package com.keduox


import java.util.Random

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Test05_cogroup {
  def main(args: Array[String]): Unit = {
    //简化日志
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    //设置流,因为数据源有2个，所以分区是2n
    val conf = new SparkConf().setMaster("local[4]").setAppName("up")
    val ssc = new StreamingContext(conf,Seconds(2))
    //获取数据流来源
    val line1 = ssc.socketTextStream("salt",9998)
    val line2= ssc.socketTextStream("salt",9999)
    //创建DS
    val l1 = line1.flatMap(_.split(" ")).map((_,new Random().nextInt()))
    val l2 = line2.flatMap(_.split(" ")).map((_,new Random().nextInt()))
    //cogroup，针对对两个或多个KV元素的RDD根据每个RDD中相同key把对应的V分别聚合成一个集合compactBuffer
    // 与reduceByKey不同的是针对两个RDD中相同的key的元素进行合并。
    val value: DStream[(String, (Iterable[Int], Iterable[Int]))] = l1.cogroup(l2)
    //比如求一个人对比上个月的销售额涨幅
    value.map(data=>{
      val value1: (Iterable[Int], Iterable[Int]) = data._2
      println(value1)
      //聚合操作求和
      (data._1,value1._1.sum,value1._2.sum)
    })
    value.print()

    //启动
    ssc.start()
    ssc.awaitTermination()
  }

}
