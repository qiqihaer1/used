package com.keduox

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

object Test_Sql {
  def main(args: Array[String]): Unit = {
    //配置模式，获取入口
    val conf = new SparkConf().setAppName("SQL").setMaster("local")
    val sc = new SparkContext(conf)
    //获取SQLContext
    val sqlc: SQLContext = new SQLContext(sc)
    //先获取一个RDD，将得到的内容拆分
    val rdd1: RDD[Array[String]] = sc.textFile("d:/sparksql.txt").map(_.split("\t"))
    //把RDD放入样例类来定义我们操作对象的字段名
      // User的age为Int，data(1)为String，需要类型转换
    val rdd2: RDD[User] = rdd1.map(data=>new User(data(0),data(1).toInt))
    //将RDD转换为dataframe,
      //在这里使用toDF默认RDD没有这个函数，需要创建隐式转换
      //sqlc为SqlContext
    import sqlc.implicits._
    val f: DataFrame = rdd2.toDF
    //想要在spark中执行sql,需要一张表,可以同DF创建一张表并命名
    val table: Unit = f.registerTempTable("haha")
    sqlc.sql("select * from haha where age>19").show()



  }

  case class User(name:String,age:Int)

}
