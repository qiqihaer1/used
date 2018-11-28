package com.keduox

import java.util
import java.util.Properties

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object testJava {
  def main(args: Array[String]): Unit = {
    var ls1=List("2","5","10")
    //定义java中的List
    var list1=new java.util.ArrayList[String]
    //给List赋值
    for(x<-ls1){
      list1.add(x)
    }
//    array(ls1)
//    array(list1)

    //使用转换工具
    import scala.collection.JavaConversions
    //java的properties转为scala的Map
    var properties=new Properties()
    properties.put("localhost","8080")
    var map=JavaConversions.propertiesAsScalaMap(properties)
//    println(map)
    //scala的Arraybuffer转为java的List
    var buf = ArrayBuffer("开心","快乐")
    //报错，需要写上全路径
    val javaList:  java.util.List[String] = JavaConversions.bufferAsJavaList(buf)
    //    println(javaList)
        //将List装换为scala的arraybuffer
        val scalaList: mutable.Buffer[String] = JavaConversions.asScalaBuffer(javaList)
    println(scalaList)
  }
//传入java的List
  /**
    * 该方法假设为java中封装好的一个List,scala需要调用它
    * @param list
    */
  def array(list:java.util.List[String])={
    println(list)
  }
}
