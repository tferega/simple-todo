package com.ferega.todo
package db

import model.{ Task, User }

import scala.collection.JavaConversions._

object TaskRepo {
  val repo = new model.repositories.TaskRepository(locator)

  def create(user: User, name: String, description: String, priority: Int): Task = {
    val task = new Task(user, name, description, priority)
    task.persist()
  }

  def getByUsername(username: String): IndexedSeq[Task] = {
    val spec = new Task.findByUser(username)
    val taskList = spec.search.toIndexedSeq
    taskList.sortBy(_.getPriority)
  }
}
