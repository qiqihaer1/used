package com.keduox.function

object function3 {
  def main(args: Array[String]): Unit = {
  //定义了method01的返回值
    method01((x:Int)=>{println("++__++");"abc"+x})

    var m2=method02 _
    //一样的结果
//    method01(m2)
//    method01(method02 _)
//    method01(method02)
  }
  //带函数参数的函数
  def method01(x:(Int)=>String):Unit ={
    println(x(5))
  }

  def method02(x:Int):String ={
    "method01"
  }


}
