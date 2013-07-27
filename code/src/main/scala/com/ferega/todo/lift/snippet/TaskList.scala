package com.ferega.todo
package lift
package snippet

import net.liftweb.http.SHtml.{ajaxText,ajaxSubmit}
import net.liftweb.util.Helpers._
import xml.Text
import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.JsCmds.SetHtml

object TaskList {
  case class Task(name: String, description: String, priority: Int)
  val taskList = IndexedSeq(
      Task("first", "asdf", 1),
      Task("second", "zxcv", 2))

  def render =
    renderTaskList &
    renderWelcome

  private def renderTaskList =
    "#tasklist *" #> taskList.map(task =>
      "#name *"        #> task.name &
      "#description *" #> task.description &
      "#priority *"    #> task.priority)

  private def renderWelcome =
    "#username" #> User.name.getOrElse("")
}
