package com.keduox

import scala.collection.mutable.HashMap

object testHashMap {
  def main(args: Array[String]): Unit = {
    //创建
    var map1 = new HashMap()
    var map2 = HashMap(("zs",10),("ls",20))
    var map3 = HashMap("zs"->10,"ls"->20)
//    println(map2)
//    println(map3)
    //插入值,插在最前面
    map2.put("ww",30)
    map3+=("ww"->30)
//    println(map2)
//    println(map3)
    //修改值
    map2("zs")=100
//    println(map2)
//    for((k,v)<-map2){
//      println(k+"|"+v)
//    }
    for(x<-map2.values){
      println(x)
    }
  }
}
