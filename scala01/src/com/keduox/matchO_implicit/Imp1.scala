package com.keduox.matchO_implicit

import com.keduox.matchO_implicit.Test03.{Bird, Person}

object Imp1 {
//  implicit def toBird(person:Test03.Person){new Test03.Bird
implicit def toBird(person:Person):Bird=new Bird()
}
