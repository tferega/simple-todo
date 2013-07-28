package com.ferega.todo
package db

import java.util.concurrent.ExecutionException

object InnerException {
  private def shedExecutionException(e: ExecutionException): Option[Throwable] =
    e.getCause match {
      case null => None
      case cause: ExecutionException => shedExecutionException(cause)
      case cause => Some(cause)
    }

  def unapply(e: ExecutionException): Option[Throwable] = {
    shedExecutionException(e)
  }
}