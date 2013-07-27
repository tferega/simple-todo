package com.ferega.todo
package db

import java.io.IOException
import java.util.concurrent.ExecutionException

object InnerException {
  def unapply(e: ExecutionException): Option[Throwable] = {
    e.getCause() match {
      case null  => None
      case cause => Some(cause)
    }
  }
}