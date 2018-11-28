package com.keduox

object testMap {
  def main(args: Array[String]): Unit = {
    //创建
    var map1: Map[Nothing, Nothing] = Map()
    var map2: Map[String, Long] = Map[String,Long]()
    //创建并赋值
    var map3 = Map("fs"->20L,"ff"->30L)
    var map4 = Map(("fs",20L),("ff",30L))
    //增加或删除，只有这种方法
    map3+=("fss"->50L)
//    println(map3)
    map3-="fs"
//    println(map3)
    //显示value
//    println(map3("ff"))
    //不能修改
//    map3("ff")=44L
//    for((k,v)<-map3){
//      println(k+"|"+v)
//    }
    for(x<-map3.keySet){
      println(x)
    }
    for(x<-map3.values){
      println(x)
    }
  }
}
