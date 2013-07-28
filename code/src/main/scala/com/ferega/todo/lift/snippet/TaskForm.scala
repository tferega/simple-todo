package com.ferega.todo
package lift
package snippet

import model.Task
import net.liftweb.common.Full
import net.liftweb.http.js.jquery.JqJsCmds
import net.liftweb.http.js.{ JsCmd, JsCmds }
import net.liftweb.http.SHtml
import net.liftweb.http.Templates
import net.liftweb.util.Helpers._

import scala.xml.{ NodeSeq, Text }

object TaskForm {
  private var name = ""
  private var description = ""

  def render =
    renderWelcome &
    tryRenderTaskTable &
    renderForm

  def processDelete(task: Task): () => JsCmd = () => {
    TaskTools.delete(task) match {
      case Right(_) =>
        JsCmds.Replace("task-"+task.getID, NodeSeq.Empty)
      case Left(message) =>
        JsCmds.SetHtml("action-result", Text(message)) & JqJsCmds.Show("action-result")
    }
  }

  def process(): JsCmd =
  TaskTools.create(name, description) match {
    case Right(task) =>
      Templates("templates-hidden" :: "task" :: Nil) match {
        case Full(template) =>
          val out = renderTask(task)(template)
          JqJsCmds.AppendHtml("task-list", out)
        case _ =>
          JsCmds.SetHtml("add-result", Text("Something went wrong! Please try again.")) & JqJsCmds.Show("add-result")
      }
    case Left(message) =>
      JsCmds.SetHtml("add-result", Text(message)) & JqJsCmds.Show("add-result")
  }

  private def tryRenderTaskTable =
    TaskTools.getForCurrentUser match {
      case Right(taskList) if taskList.isEmpty => renderEmptyTable
      case Right(taskList) => renderTaskList(taskList)
      case Left(message)   => renderTaskError(message)
    }

  private def renderEmptyTable =
    "#task-table" #> "You don't seem to have any tasks."

  private def renderTaskList(taskList: IndexedSeq[Task]) =
    ".task" #> taskList.map(renderTask)

  private def renderTask(task: Task) =
    ".task [id]" #> ("task-"+task.getID) &
    ".name *"         #> task.getName &
    ".description *"  #> task.getDescription &
    ".priority *"     #> task.getPriority.opt.pretty &
    ".delete *"       #> SHtml.ajaxButton("Delete", processDelete(task))

  private def renderTaskError(message: String) =
    "#task-table" #> message

  private def renderWelcome =
    "#username" #> Session.get.getUsername



  def renderForm =
    "#add-name"        #> SHtml.ajaxText("", name = _) &
    "#add-description" #> SHtml.ajaxText("", description = _) &
    "#add-submit"      #> SHtml.ajaxSubmit("Add", process)
}