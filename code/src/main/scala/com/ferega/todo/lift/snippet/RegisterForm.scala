package com.ferega.todo
package lift
package snippet

import net.liftweb.http.js.jquery.JqJsCmds.Show
import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.JsCmds.{ RedirectTo, SetHtml }
import net.liftweb.http.SHtml
import net.liftweb.util.Helpers._

import xml.Text

object RegisterForm {
  var username = ""
  var password = ""

  def process(): JsCmd =
    UserTools.create(username, password) match {
      case Right(userData) =>
        Session.create(userData)
        RedirectTo("/")
      case Left(message) =>
        SetHtml("register-result", Text(message)) & Show("register-result")
    }

  def render =
    "#register-name" #> SHtml.ajaxText("", username = _) &
    "#register-pass" #> SHtml.password("", password = _) &
    "#register-submit" #> SHtml.ajaxSubmit("Register", process)
}