package com.keduox.function

import scala.annotation.tailrec

object function_recursive {
  def main(args: Array[String]): Unit = {
//    println(method01(10))
//    println(method03(10))
    println(method04(10,1))
  }

//递归函数

  def method01(x:Int) :BigInt={
    //出口设置
    if (x==1){
      1
    }else{
      //累加
      x+method01(x-1)
    }
  }

  //嵌套函数
  def method02(x:Int):BigInt={

    def methodInner(x:Int,result:Int):BigInt={
    //出口设置
    if (x==1){
      //当x=1时返回result!!!
      result
    }else{
      println(result)
      methodInner(x-1,result+x)
      }
    }
    //
    methodInner(x,1)
  }

  //尾递归函数
  def method03(x:Int):BigInt={
  @tailrec
  def methodInner(x:Int,result:Int):BigInt={
    //出口设置
    if (x==1){
      //当x=1时返回result!!!
      result
    }else{
      println(result)
      methodInner(x-1,result+x)
    }
  }
  //
  methodInner(x,1)
  }

  @tailrec
  def method04(x:Int,b:Int) :BigInt={
    //出口设置
    if (x==1){
      b
    }else{
      //累加
      method04(x-1,b+x)
    }
  }
}
