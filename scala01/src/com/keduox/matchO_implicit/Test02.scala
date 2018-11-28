package com.keduox.matchO_implicit

object Test02 {
  def main(args: Array[String]): Unit = {
    val haha = new Hello("姚明")
    val hehe = new Bye("小明")
    //有三个类且这三个类没有关系时，
    //    test(haha)
    //当一个普通方法，参数是一个类时，当传入其他类时会报错
    test(hehe)
  }

  def test(hello: Hello): Unit = {
    println(hello.name)
  }

  //  implicit def toHello(obj:Object):Hello={
  //    //classof[] 获取类的类型
  //    if(obj.getClass==classOf[Bye]){
  //      val n=obj.asInstanceOf[Bye]
  //      new Hello(n.name)
  //    }else{
  //      null
  //    }
  //  }
  implicit def toHello(bye: Bye): Hello = {
    new Hello(bye.name)
  }

  class Hello(var name: String)

  class Bye(var name: String)

}

