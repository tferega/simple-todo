package com.ferega.todo
package lift

import db.TaskRepo
import model.Task

import scala.concurrent.Await

object TaskTools {
  def currentUser = Session.opt orElse Request.opt getOrElse(throw new Exception("Neither session nor request are defined"))

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
      val userTaskListFut = TaskRepo.findByUser(currentUser)
      val userTaskList = Await.result(userTaskListFut, reasonableTimeout)
      Right(userTaskList)
    } catch {
      case e: Exception =>
        Left("Something went wrong! Please try again.")
    }
  }

  def getForCurrentUser(id: String): Either[String, Option[Task]] = {
    try {
      val taskOptFut = TaskRepo.find(id)
      val taskOpt = Await.result(taskOptFut, reasonableTimeout)
      Right(taskOpt.filter(_.getUserID == currentUser.getUsername))
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
