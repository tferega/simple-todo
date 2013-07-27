package com.ferega.todo
package lift
package snippet

import net.liftweb.http.js.jquery.JqJsCmds
import net.liftweb.http.js.{ JsCmd, JsCmds }
import net.liftweb.http.SHtml
import net.liftweb.util.Helpers._

import xml.Text

object LoginForm {
  var username = ""
  var password = ""

  def process(): JsCmd =
    UserTools.auth(username, password) match {
      case Right(user) =>
        Session.create(user)
        JsCmds.RedirectTo("/")
      case Left(message) =>
        JsCmds.SetHtml("login-result", Text(message)) & JqJsCmds.Show("login-result")
    }

  def render =
    "#login-name" #> SHtml.ajaxText("", username = _) &
    "#login-pass" #> SHtml.password("", password = _) &
    "#login-submit" #> SHtml.ajaxSubmit("Log in", process)
}