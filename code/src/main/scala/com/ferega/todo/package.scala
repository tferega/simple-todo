package com.ferega

import com.dslplatform.client.Bootstrap

package object todo {
  val home   = System.getProperty("user.home")
  val config = home + "/simple-todo/simple-todo.ini"

  val locator = Bootstrap.init(config)
}