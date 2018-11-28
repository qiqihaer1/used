package com.keduox

import org.apache.spark.{SparkConf, SparkContext}

object Test01 {
 //本地开发时，不需要启动服务器集群
  //本地运行是在一个JVM进程中运行
  def main(args: Array[String]): Unit = {
    var conf = new SparkConf()
    //本地运行时设置为local，集群模式为主机名:7077
    conf.setMaster("local")
    //设置应用名称
    conf.setAppName("pika")
    val sc = new SparkContext(conf)
    val collect = sc.textFile("D:\\hello.txt").flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).foreach(println)
    sc.stop()

  }
  //spark程序的入口为sparkContext

}
