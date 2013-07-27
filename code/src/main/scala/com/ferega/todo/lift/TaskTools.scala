package com.ferega.todo
package lift

import db.{ TaskData, TaskRepo }

object TaskTools {
  def getForCurrentUser(): Either[String, IndexedSeq[TaskData]] = {
    try {
      Right(TaskRepo.getByUsername(Session.get.username))
    } catch {
      case e: Exception =>
        Left("Something went wrong! Please try again.")
    }
  }
}
