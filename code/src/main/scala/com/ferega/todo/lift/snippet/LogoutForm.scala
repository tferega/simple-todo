package com.ferega.todo
package lift
package snippet

import net.liftweb.http.js.{ JsCmd, JsCmds }
import net.liftweb.http.SHtml
import net.liftweb.util.Helpers._

object LogoutForm {
  def process(): JsCmd = {
    Session.destroy
    JsCmds.RedirectTo("/auth")
  }

  def render =
    "#logout" #> SHtml.ajaxSubmit("Log out", process)
}