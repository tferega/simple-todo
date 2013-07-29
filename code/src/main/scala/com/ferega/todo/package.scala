package com.ferega

import com.dslplatform.client.Bootstrap

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext

package object todo {
  val home   = System.getProperty("user.home")
  val config = home + "/.config/simple-todo/simple-todo.ini"

  val locator = Bootstrap.init(config)

  val reasonableTimeout = 5 seconds

  implicit val ec = ExecutionContext.fromExecutor(java.util.concurrent.Executors.newCachedThreadPool())

  implicit class ImpaleInteger(i: Integer) {
    def opt = Option(i).map(_.toInt)
  }

  implicit class ImpaleOption[T](opt: Option[T]) {
    def pretty = opt.map(_.toString).getOrElse("N/A")
  }

  def tryO[T](f: => T): Option[T] =
    try {
      Some(f)
    } catch {
      case e: Exception => None
    }
}
