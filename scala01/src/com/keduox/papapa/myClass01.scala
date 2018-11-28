package com.keduox.papapa

import scala.beans.BeanProperty

class myClass01 {
//  private String name;错误
  //scala的成员变量必须手动初始值
  // _为null的意思,都可以用
  @BeanProperty var name:String=_


  def method01():Unit={
    println("myClass01的方法1")
  }

}
