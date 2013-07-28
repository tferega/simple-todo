package com.ferega.todo
package lift

import db.TaskRepo
import model.Task

import scala.concurrent.Await

object TaskTools {
  def currentUser = Session.opt orElse Request.opt getOrElse(throw new Exception("Neither session nor request are defined"))

  def create(name: String, description: String, priority: Option[Int] = None): Either[String, Task] = {
    try {
      val newTaskFut = TaskRepo.create(currentUser, name, description, priority)
      val newTask = Await.result(newTaskFut, reasonableTimeout)
      Right(newTask)
    } catch {
      case e: Exception =>
        Left("Something went wrong! Please try again.")
    }
  }

  def create(task: Task): Either[String, Task] = {
    try {
      task.setUser(currentUser)
      val newTaskFut = TaskRepo.create(task)
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

  def updateForCurrentUser(mutatedTask: Task): Either[String, Task] = {
    try {
      if (mutatedTask.getUserID == currentUser.getUsername) {
        val updatedTaskFut = TaskRepo.update(mutatedTask)
        val updatedTask = Await.result(updatedTaskFut, reasonableTimeout)
        Right(updatedTask)
      } else {
        Left("Something went wrong! Please try again.")
      }
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
