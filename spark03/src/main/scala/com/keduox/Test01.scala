package com.keduox

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object Test01 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("sqlS")
    val sc = new SparkContext(conf)
    val context = new SQLContext(sc)
    //构建DataFrame
    val dataFrame = context.read.json("d:/spark_eg.json")
    //查找DF的字段
//    dataFrame.printSchema()
    //只显示该字段
//    dataFrame.select("age").show()
    //对age字段的内容进行操作
//    dataFrame.select(dataFrame("age")*5).show()
    //对age字段的内容进行判断，和SQL的where一样
//    dataFrame.filter(dataFrame("age")>19).show()
//    dataFrame.filter(dataFrame("age")===21).show()
    //select age,count(age) from table_name group by age
    //按age分类并对结果进行统计
    dataFrame.groupBy("age").count().show()
  }
}
