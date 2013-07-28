package com.ferega.todo
package lift

import db.TaskRepo
import model.Task

import scala.concurrent.Await

object TaskTools {
  def create(name: String, description: String): Either[String, Task] = {
    try {
      val user = Session.get
      val newTaskFut = TaskRepo.create(user, name, description, None)
      val newTask = Await.result(newTaskFut, reasonableTimeout)
      Right(newTask)
    } catch {
      case e: Exception =>
        Left("Something went wrong! Please try again.")
    }
  }

  def getForCurrentUser(): Either[String, IndexedSeq[Task]] = {
    try {
      val user = Session.get
      val userTaskListFut = TaskRepo.findByUser(user)
      val userTaskList = Await.result(userTaskListFut, reasonableTimeout)
      Right(userTaskList)
    } catch {
      case e: Exception =>
        Left("Something went wrong! Please try again.")
    }
  }

  def delete(task: Task): Either[String, Task] = {
    try {
      val deletedTaskFut = TaskRepo.delete(task)
      val deletedTask = Await.result(deletedTaskFut, reasonableTimeout)
      Right(deletedTask)
    } catch {
      case e: Exception =>
        Left("Something went wrong! Please try again.")
    }
  }
}
