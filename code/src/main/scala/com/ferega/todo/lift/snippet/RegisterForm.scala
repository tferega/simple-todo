package com.ferega.todo
package lift
package snippet

import net.liftweb.http.js.JsCmd
import net.liftweb.http.SHtml
import net.liftweb.util.Helpers._

object RegisterForm {
  var username = ""
  var password = ""

  def process(): JsCmd = {
    println(s"REGISTER: $username, $password")
  }

  def render =
    "#register-name" #> SHtml.ajaxText("", username = _) &
    "#register-pass" #> SHtml.password("", password = _) &
    "#register-submit" #> SHtml.ajaxSubmit("Register", process)
}