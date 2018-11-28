package com.keduox

import scala.collection.mutable.ListBuffer

object testListBuffer {
  def main(args: Array[String]): Unit = {
    var list1 = ListBuffer("dada","pengliyuan","sicong","wanger")
    //ListBuffer的特点
    list1+="sb"
    list1+=("2B","dd")
//    println(list1)
    list1.append("shen","bad")
//    println(list1)
    list1-="sb"
//    println(list1)
    val list2 = ListBuffer("t1","t2","t3","t4")
    list1++=list2
    println(list1.mkString(","))
  }
}
