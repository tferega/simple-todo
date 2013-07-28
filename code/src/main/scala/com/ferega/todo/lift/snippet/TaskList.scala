package com.ferega.todo
package lift
package snippet

import model.Task

import net.liftweb.http.js.jquery.JqJsCmds
import net.liftweb.http.js.{ JsCmd, JsCmds }
import net.liftweb.http.SHtml
import net.liftweb.util.Helpers._

import scala.xml.{ NodeSeq, Text }

object TaskList {
  def render =
    tryRenderTaskTable &
    renderWelcome

  def processDelete(task: Task): () => JsCmd = () => {
    TaskTools.delete(task) match {
      case Right(_) =>
        JsCmds.Replace("task-"+task.getID, NodeSeq.Empty)
      case Left(message) =>
        JsCmds.SetHtml("action-result", Text(message)) & JqJsCmds.Show("action-result")
    }
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
    "#task-list" #> taskList.map(task =>
      "#task-list [id]" #> ("task-"+task.getID) &
      ".name *"         #> task.getName &
      ".description *"  #> task.getDescription &
      ".priority *"     #> task.getPriority &
      ".delete *"       #> SHtml.ajaxButton("Delete", processDelete(task)))

  private def renderTaskError(message: String) =
    "#task-table" #> message

  private def renderWelcome =
    "#username" #> Session.get.getUsername
}
