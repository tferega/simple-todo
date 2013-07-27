package com.ferega.todo
package lift
package snippet

import net.liftweb.http.js.jquery.JqJsCmds.Show
import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.JsCmds.{ RedirectTo, SetHtml }
import net.liftweb.http.SHtml
import net.liftweb.util.Helpers._

import xml.Text

object LoginForm {
  var username = ""
  var password = ""

  def process(): JsCmd =
    UserTools.logIn(username, password) match {
      case Right(_)      => RedirectTo("/")
      case Left(message) => SetHtml("login-result", Text(message)) & Show("login-result")
    }

  def render =
    "#login-name" #> SHtml.ajaxText("", username = _) &
    "#login-pass" #> SHtml.password("", password = _) &
    "#login-submit" #> SHtml.ajaxSubmit("Log in", process)
}