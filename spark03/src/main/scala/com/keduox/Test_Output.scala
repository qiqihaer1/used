package com.keduox

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, SQLContext}

object
Test_Output {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("sqlO")
    val sc = new SparkContext(conf)
    val context = new SQLContext(sc)
    //构建DataFrame，取出数据构成DF
    val dataFrame = context.read.json("d:/ssr.json")

    //用DF进行储存的办法，目录不能重名
//    dataFrame.write.save("D:/spark_eg1")
    //储存成json格式
    dataFrame.write.format("json").save("D:/spark_eg2")
    //从SQL数据库取数据,用的是read的API
//    val stringToString = Map("url"->"jdbc:mysql://192.168.200.21/mysql?user=root&password=Keduox1234!@#",
//      "dbtable" -> "user")
//    val load = context.read.format("jdbc").options(stringToString).load
//    load.show()
    //将DF储存为其他文件格式
    import context.implicits._
    //不用样例类直接使用toDF()指定schema,但这种方法无法指定字段名类型！！！！类型全是String
//    val dataFrame1= sc.makeRDD(1 to 10).map((_,1)).toDF("编号","个数").write.format("json").save("D:/spark_eg3")
    sc.stop()
  }

}
