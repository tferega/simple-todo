package com.ferega.todo
package db

import java.io.IOException

object IsDuplicate {
  def unapply(e: IOException): Boolean =
    e.getMessage().contains("duplicate key value violates unique constraint")
}