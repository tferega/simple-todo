package com.ferega.todo
package lift
package snippet

import db.TaskData

import net.liftweb.util.Helpers._

object TaskList {
  def render =
    tryRenderTaskTable &
    renderWelcome

  private def tryRenderTaskTable =
    TaskTools.getForCurrentUser match {
      case Right(taskList) if taskList.isEmpty => renderEmptyTable
      case Right(taskList) => renderTaskList(taskList)
      case Left(message)   => renderTaskError(message)
    }

  private def renderEmptyTable =
    "#task-table" #> "You don't seem to have any tasks."

  private def renderTaskList(taskList: IndexedSeq[TaskData]) =
    "#task-list *" #> taskList.map(task =>
      "#name *"        #> task.name &
      "#description *" #> task.description &
      "#priority *"    #> task.priority)

  private def renderTaskError(message: String) =
    "#task-table" #> message

  private def renderWelcome =
    "#username" #> UserTools.name
}
