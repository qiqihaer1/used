package com.keduox

object work {
  def main(args: Array[String]): Unit = {
//    println(countDown(11))
//    count()
    count99()
  }

  def countDown(n:Int)={
    var total=for(i<-1 to n reverse)yield (i)
    total
  }

  def count(): Unit ={
    var total=for(a<-1 to 9;b<-a to 9)yield
      (if(b!=9){print(a+"×"+b+"="+a*b+"   ")}
      else if(b==9)println{a+"×"+b+"="+a*b}
      )
  }
  def count99()={
    var total=for(b<-1 to 9;a<-1 to b)yield
      (if(b!=a){print(a+"×"+b+"="+a*b+"   ")}
      else if(b==a)println{a+"×"+b+"="+a*b}
        )
  }

}
