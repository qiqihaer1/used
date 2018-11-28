package com.keduox.function

object function_high {
  def main(args: Array[String]): Unit = {
    //a是结果，b是循环数
//    (1 to 10).reduce((a,b)=>{
//      println("a:"+a)
//      println("b:"+b)
//      a+b
//    })
    //上面是reduce的详细写法
//    val i = (1 to 10).reduce(_+_)
//    print(i)
//  }
  //map
//    (1 to 10).map(_*10).foreach(println _)
    List("hello java","hello world").map(_.split(" ")).foreach(println _)

  }
}
