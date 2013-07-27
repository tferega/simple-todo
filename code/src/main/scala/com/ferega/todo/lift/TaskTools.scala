package com.ferega.todo
package lift

import db.TaskRepo
import model.Task

object TaskTools {
  def getForCurrentUser(): Either[String, IndexedSeq[Task]] = {
    try {
      Right(TaskRepo.getByUsername(Session.get.getUsername))
    } catch {
      case e: Exception =>
        Left("Something went wrong! Please try again.")
    }
  }
}
