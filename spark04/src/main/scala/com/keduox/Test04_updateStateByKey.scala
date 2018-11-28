package com.keduox

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Test04_updateStateByKey {
  def main(args: Array[String]): Unit = {
    //简化日志
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    //设置流
    val conf = new SparkConf().setMaster("local[2]").setAppName("up")
    val ssc = new StreamingContext(conf,Seconds(2))
    ssc.checkpoint("d:/checkpoint")
    //获取数据流来源
    val line = ssc.socketTextStream("salt",9998)
    //获取数据DS并操作
    val value: DStream[(String, Int)] = line.flatMap(_.split(" ")).map((_,1)).updateStateByKey(updateFunc)
    //执行
    value.print(15)
    //启动
    ssc.start()
    ssc.awaitTermination()
  }

  /**
    * updateStateByKey的处理方法
    * 该方法是一条记录进入一次，还是一批数据直接进来？
    * 按key进行分组之后，将同一个key的数据放在一个Seq当中。所以该方法会存在每一个key调用一次
    * @param seq 每一个key的值的seq
    * @param opt 上一次对于该key的处理结果
    * @return
    */
  def updateFunc(seq:Seq[Int],opt:Option[Int]): Option[Int] ={
    //出现DStream里数据的key的value,有多条就有多个
    println("seq:"+seq)
    //上次的Some的值
    println("opt:"+opt)
    //累加同个key的value
    val su=seq.sum
    Some(opt.getOrElse(0)+su)
  }
}
