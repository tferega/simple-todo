package com.ferega.todo
package lift

import db.TaskRepo
import model.Task

object TaskTools {
  def create(name: String, description: String): Either[String, Task] = {
    try {
      Right(TaskRepo.create(Session.get, name, description, 1))
    } catch {
      case e: Exception =>
        Left("Something went wrong! Please try again.")
    }
  }

  def getForCurrentUser(): Either[String, IndexedSeq[Task]] = {
    try {
      Right(TaskRepo.getByUsername(Session.get.getUsername))
    } catch {
      case e: Exception =>
        Left("Something went wrong! Please try again.")
    }
  }
}
