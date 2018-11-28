package com.keduox.function

object function4 {
  def main(args: Array[String]): Unit = {

    val stringToUnit: String => Unit = m1()
    stringToUnit("hhel")


    val unit1: Unit = method01((x:Int)=>{x+""})
    method01((x:Int)=>x+"")
    method01((x)=>x+"")
    method01(x=>x+"")
    method01(_+"")

  }

  //返回一个函数的函数
  def m1()={
    1
    (x:String)=>{print(x)}
  }

  def method01(x:(Int)=>String):Unit ={
    "method01"
  }

}
