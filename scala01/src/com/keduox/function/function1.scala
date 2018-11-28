package com.keduox.function

object function1 {
  def main(args: Array[String]): Unit = {
    var haha=NB1 _
    println(haha("abc"))
  }

  def NB1(x:String):Int ={
    println("++++++++++++++++++++")
    123
  }
}
