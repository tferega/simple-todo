package com.ferega.todo
package lift
package snippet

import net.liftweb.http.js.jquery.JqJsCmds
import net.liftweb.http.js.{ JsCmd, JsCmds }
import net.liftweb.http.SHtml
import net.liftweb.util.Helpers._

import xml.Text

object RegisterForm {
  var username = ""
  var password = ""

  def process(): JsCmd =
    UserTools.create(username, password) match {
      case Right(user) =>
        Session.create(user)
        JsCmds.RedirectTo("/")
      case Left(message) =>
        JsCmds.SetHtml("register-result", Text(message)) & JqJsCmds.Show("register-result")
    }

  def render =
    "#register-name" #> SHtml.ajaxText("", username = _) &
    "#register-pass" #> SHtml.password("", password = _) &
    "#register-submit" #> SHtml.ajaxSubmit("Register", process)
}