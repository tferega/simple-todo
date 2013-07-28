package com.ferega.todo
package lift
package snippet

import net.liftweb.http.js.jquery.JqJsCmds
import net.liftweb.http.js.{ JsCmd, JsCmds }
import net.liftweb.http.SHtml
import net.liftweb.util.Helpers._

import xml.Text

object AddTaskForm {
  private var name = ""
  private var description = ""

  def process(): JsCmd =
    TaskTools.create(name, description) match {
      case Right(task) =>
        JqJsCmds.AppendHtml("task-body",
          <tr id={ "task-"+task.getID }>
            <td>{ task.getName }</td>
            <td>{ task.getDescription }</td>
            <td>{ task.getPriority }</td>
            <td>{ SHtml.ajaxButton("Delete", TaskList.processDelete(task)) }</td>
          </tr>)
      case Left(message) =>
        JsCmds.SetHtml("add-result", Text(message)) & JqJsCmds.Show("add-result")
    }

  def render =
    "#name"        #> SHtml.ajaxText("", name = _) &
    "#description" #> SHtml.ajaxText("", description = _) &
    "#submit"      #> SHtml.ajaxSubmit("Add", process)
}