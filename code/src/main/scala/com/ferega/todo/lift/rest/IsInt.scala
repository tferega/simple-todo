package com.ferega.todo
package lift
package rest

object IsInt {
  def unapply(str: String): Option[Int] =
    tryo {
      str.toInt
    }
}