package com.keduox.matchO_implicit

object Test01 {
  def main(args: Array[String]): Unit = {
    var name="ls"
    var arr01=Array("ls","xm","kd","bb")
    var list01=List("ls","xm","kd","bb")
//    name match {
//      case "zs"=>println("名字为ZS")
//      case "l"=>println("名字为LL")
//      case _=>println("不满足条件")
//    }
//    arr01 match {
//      case Array("ls")=>println("数组里只有ls")
//      case Array("ls",_*)=>println("数组以ls开头")
//      case Array(x,y,z,v)=>println("数组有4个元素")
//      case _=>println("不满足条件")
//    }

    list01 match {
//      case List("ls")=>println("链表里只有ls")
      case "ls"::Nil=>println("链表里只有ls")
//      case List("ls",_*)=>println("链表以ls开头")
//      case "ls"::tail=>println("链表以ls开头")
//      case List(x,y,z,v)=>println("链表有4个元素")
      case x::y::z::v::Nil=>println("链表有4个元素")

      case _=>println("不满足条件")
    }
  }
}
