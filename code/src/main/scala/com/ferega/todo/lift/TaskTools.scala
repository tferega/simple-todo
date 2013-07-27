package com.ferega.todo
package lift

import db.{ TaskData, TaskRepo }

object TaskTools {
  def getForCurrentUser(): Either[String, IndexedSeq[TaskData]] = {
    try {
      Right(TaskRepo.getByUsername(UserTools.name))
    } catch {
      case e: Exception =>
        Left("Something went wrong! Please try again.")
    }
  }
}
