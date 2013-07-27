package com.ferega.todo
package db

import scala.collection.JavaConversions._

object TaskRepo {
  val repo = new model.repositories.TaskRepository(locator)

  private def toTaskData(task: model.Task) =
    TaskData(task.getName, task.getDescription, task.getPriority)

  def getByUsername(username: String): IndexedSeq[TaskData] = {
    val spec = new model.Task.findByUser(username)
    val taskList = spec.search.toIndexedSeq map toTaskData
    taskList.sortBy(_.priority)
  }
}

case class TaskData(name: String, description: String, priority: Int)
