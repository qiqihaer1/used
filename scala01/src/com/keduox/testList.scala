package com.keduox
import Array._
object testList {
  def main(args: Array[String]): Unit = {
    var list = List()
    var list1 = List("h1","h2","h3","h4")
    var list2 = 32::43::33::Nil
    var list3 = "SE"::"SD"::"SB"::"24"::Nil
//    println(list1)
//    println(list2)
    //取值
//    println(list1.head)
//    println(list1.tail)
    //判断是List否为空
//     println(list1.isEmpty)
    //多个List连接,创建一个新List
    var list4 = list1:::list2
//    println(list4)
//    println(list1.contains(33L))
    // 满足条件的集合结果,类型必须相同
    list2.filter(x=>x%2==0).foreach(println(_))
//    list3.filter(d=>d.startsWith("S")).foreach(println _)
  }
}
