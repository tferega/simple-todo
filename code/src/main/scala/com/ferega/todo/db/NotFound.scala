package com.ferega.todo
package db

import java.io.IOException

object NotFound {
  def unapply(e: IOException): Boolean =
    e.getMessage().contains("Can't find")
}