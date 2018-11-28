package com.keduox

object testFunction03 {
  def main(args: Array[String]): Unit = {
//    println(function1())
//    println(function2(35))
//    println(function3(35))
//    println(function4(10,"dada"))
//    println(function5(5))
//    function6(5,1,4)
    function7(5)
  }
  def function1(): Unit ={
    println("00000000000000000000000")
  }

  def function2(n:Int): Int ={
    n
  }

  def function3(n:Int)={
    "fsf"
  }

  def function4(a:Int,b:String)={
    a+b
  }

  def function5(n:Int)={
    if(n>10){
      "abc"
    }else{
      "dd"
    }
  }

  def function6(n:Int*)={
    for(a<-n){
      println(a)
    }
  }

  def function7(a:Int,b:Int=3)={
      println(a*b)
  }
}
