package com.keduox.papapa

object myClass01Test {
  def main(args: Array[String]): Unit = {
    //private声明会导致无法调用类,不声明就是public,外部可调用
    //val修饰的变量只会有get方法，不能修改
    var class01 = new myClass01
//    class01.name="智障"//.name 即为name的getter方法
    class01.setName("zs")
    println(class01.name)

//    println(class01.name)
    class01.method01()
  }

}
