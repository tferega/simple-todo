package com.ferega.todo
package lift
package snippet

import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.JsCmds.RedirectTo
import net.liftweb.http.SHtml
import net.liftweb.util.Helpers._

import xml.Text

object LogoutForm {
  def process(): JsCmd = {
    User.logOut
    RedirectTo("/auth")
  }

  def render =
    "#logout" #> SHtml.ajaxSubmit("Log out", process)
}