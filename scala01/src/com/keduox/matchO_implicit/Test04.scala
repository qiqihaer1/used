package com.keduox.matchO_implicit

object Test04 {
  def main(args: Array[String]): Unit = {
    //隐式参数的使用
    val sp = new Man(5)
//    run("小米",sp)
    //隐式参数的使用
    run("小米")
  }

//  def run(name:String,speed:Man): Unit ={
//    print(name+"跑了起来，速度为"+speed.name)
//}

  //定义一个带隐式参数的函数
  implicit var speed:Man=new Man(5)

  def run(name:String)(implicit speed:Man): Unit ={
    print(name+"跑了起来，速度为"+speed.name)
  }
  //定义一个类
  class Man(n:Int){
    var name=n
  }
}
