package com.keduox

import Array._
import scala.collection.mutable.ArrayBuffer
import scala.util.Sorting


object testArrayBuffer {
  def main(args: Array[String]): Unit = {
    //创建可变数组
    var a1=new ArrayBuffer[String]()
    var a2=ArrayBuffer[String]()
    //创建赋初始值
    var a3=ArrayBuffer("ds","converse")
    var a4=ArrayBuffer("1","2")
    var a5=Array("10","13","11","20")
    //可变数组的特点，增加
    a3+="hapi"
    //追加赋值多个,ArrayBuffer的特点
    a3+=("shabi","2b")
//    println(a3)
    //将一个数组的值追加到另一个数组
    a3++=a4++=a5
//    println(a3)
    //指定下标处插入数据
    a3.insert(0,"god")
//    println(a3)
    //可变数组的特点，删除1个或多个
    a3-=("10","1")
//    println(a3)
    //按下标删除1个或多个
    a3.remove(0,1)
//    println(a3)
    //可变数组变不可变数组
    var a33:Array[String]=a3.toArray
//    println(a33.mkString(","))
//不可变数组变可变数组
    val a333 = a33.toBuffer
    a333+="吃鸡"
//    println(a333.mkString(","))
    //拿出数组中的极值
//    println(a5.mkString(","))
//    println(a5.min,a5.max)
    //必须导入包import Array._，sum只能计算Int泛型的数组
    val abb = range(1,20)
//    println(abb.sum)
    val abc = ArrayBuffer[Int](24,232,1,0,100,222)
    //排序 前面的元素小于后面的元素
//    println(abc.sortWith(_ < _))
    //需要将Arraybuffer类型转为Array才能使用quickSort
    val abcd = abc.toArray
    Sorting.quickSort(abcd)
    println(abcd.mkString(","))

//    println(abc.sortBy(5 >= _))
  }
}
