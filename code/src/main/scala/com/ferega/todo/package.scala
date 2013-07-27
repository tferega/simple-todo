package com.ferega

import com.dslplatform.client.Bootstrap

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext

package object todo {
  val home   = System.getProperty("user.home")
  val config = home + "/.config/simple-todo/simple-todo.ini"

  val locator = Bootstrap.init(config)

  val dbTimeout = 5 seconds

  implicit val ec = ExecutionContext.fromExecutor(java.util.concurrent.Executors.newCachedThreadPool())
}
