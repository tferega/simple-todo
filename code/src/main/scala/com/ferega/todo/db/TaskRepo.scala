package com.ferega.todo
package db

import model.Task

import scala.collection.JavaConversions._

object TaskRepo {
  val repo = new model.repositories.TaskRepository(locator)

  def getByUsername(username: String): IndexedSeq[Task] = {
    val spec = new model.Task.findByUser(username)
    val taskList = spec.search.toIndexedSeq
    taskList.sortBy(_.getPriority)
  }
}
