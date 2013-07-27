package com.ferega.todo
package lift
package snippet

import net.liftweb.http.js.JsCmd
import net.liftweb.http.SHtml
import net.liftweb.util.Helpers._

object LoginForm {
  var username = ""
  var password = ""

  def process(): JsCmd = {
    println(s"LOGIN: $username, $password")
  }

  def render =
    "#login-name" #> SHtml.ajaxText("", username = _) &
    "#login-pass" #> SHtml.password("", password = _) &
    "#login-submit" #> SHtml.ajaxSubmit("Log in", process)
}