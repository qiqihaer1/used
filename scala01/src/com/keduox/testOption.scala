package com.keduox

object testOption {
  def main(args: Array[String]): Unit = {
    //Option取Some值的用法
    println(method1(4).getOrElse("没有找到崔化钠"))

  }
  //返回Some[T]或None
  //当从数据库拿值的时候，拿到值返回,没有拿到返回None
  def method1(n:Int): Option[String] ={
    if(n==5){
      //只能返回Some[T]或None
      return Some("崔化钠")
    }else{
      return None
    }

  }
}
