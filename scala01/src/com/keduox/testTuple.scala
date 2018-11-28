package com.keduox

object testTuple {
  def main(args: Array[String]): Unit = {
    //创建
    val tuple =(14,"fs","ds",11,3)
    //创建长度确定的Tuple,最多22个
    val tuple2 = Tuple2(14,"fs")
    val tuple5 = Tuple5(14,"fs","ds",11,3)
    //取值
    println(tuple._2)
  }

}
