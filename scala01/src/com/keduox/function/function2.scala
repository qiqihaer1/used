package com.keduox.function

object function2 {
  def main(args: Array[String]): Unit = {
    var ff = (age:Int)=>
    {println("_______________")
    "匿名函数"}
    ff(10)
    println(ff(10))
  }
}
