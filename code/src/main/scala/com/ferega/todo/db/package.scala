package com.ferega.todo

import java.util.concurrent.{ Future => JFuture }

import scala.concurrent.Future

package object db {
  def wrapJavaFuture[T](jf: => JFuture[T]): Future[T] = Future {
    try {
      jf.get
    } catch {
      case InnerException(e) => throw e
    }
  }
}