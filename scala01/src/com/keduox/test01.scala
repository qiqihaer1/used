package com.keduox

object test01 {
  def main(args: Array[String]): Unit = {
    var b=5
    var a=8

//    println("hello scala!")
    //&&前面为false,则后面的判断不会执行
    println(b>a&&b+5>a)
    println(b)
    //&后面的判断会执行
    println(b>a&b+5>a)
    println(b)
    //位运算符&,位运算，都为1则为1，否则为0
//    println(a&b)
    //位运算符|,位运算，有一个为1则为1，否则为0
//    println(5|9)
  //位运算符<<,位运算，左移2位
    println(1<<2)

  }
}
