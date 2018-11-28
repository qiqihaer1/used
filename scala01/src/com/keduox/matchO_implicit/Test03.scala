package com.keduox.matchO_implicit

object
Test03 {
  def main(args: Array[String]): Unit = {
    var bird = new Bird
    var person = new Person
//    bird.fly
  //赋给person没有的方法
//    implicit def toBird(person:Person):Bird=new Bird()

  //引入方式的隐式转换
    import com.keduox.matchO_implicit.Imp1._
    person.fly()
  }

  class Animal(){
    println("动物能飞起来")
  }

  class Bird(){
    def fly(): Unit ={
      println("鸟能飞起来")
    }
  }
  class Person()

}
