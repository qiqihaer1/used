package com.keduox
import Array._
object testArray {
  def main(args: Array[String]): Unit = {
    //创建数组
    var array01:Array[Int]= new Array(3)
    //向数组赋值
    array01(0)=453
    array01(1)=28
//    println(array01(0))
    //创建数组并赋值
    var array02:Array[Int] =Array(23,12,243,53)
//    println(array02(0))
    //循环遍历
//    array02.foreach(println _)
//    var x=array02.foreach(x=>println(x))
//    for(x<-0 until array02.length){println(array02(x))}
//    for(x<-array02){println(x)}
    //将Int数组转为String数组,以,分隔每个数
  var array02S:String=array02.mkString(",")
//    println(array02S)
    //将Int数组转为String数组,以,分隔每个数,<加在头部，>加在尾部
//    println(array02.mkString("<",",",">"))
    //取数组前2个值
    val ints: Array[Int] = array02.take(2)
//    ints.foreach(println(_))
//    println(array02.take(2).mkString(","))
    //数组的拼接,泛型要相同,要引包
    val ints1 = concat(array02, array01)
//   ints1.foreach(println(_))
    //创建数组并赋值连续的值 ,不包括末尾值
    val ints2: Array[Int] = range(1,22)
    //等价于range(1,22)
    val ints22 = 1 to 21
    val ints222 = 1.to(21)
//    ints2.foreach(println(_))
    ints22.foreach(println(_))
    ints222.foreach(println(_))


}
}
