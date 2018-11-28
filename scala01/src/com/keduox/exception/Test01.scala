package com.keduox.exception

object Test01 {
  def main(args: Array[String]): Unit = {
    try{
      mmm(13)
//      var a=3/0
//      print(a)
    }catch {
      case a:MyException=>println("你才没满18"+a.getMessage)
      case a:NullPointerException=>println("找不到！")
      case b:ArithmeticException=>println("值为null")
      case c:Exception=>println("拜托！")
    }

    def mmm(n:Int)={
      if(n<18){
        throw new MyException("未满18岁禁止进入")
      }else{
        println("ok")
      }

    }

  }
}
