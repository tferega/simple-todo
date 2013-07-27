package com.ferega.todo
package lift

import db.{ IsDuplicate, UserRepo }
import model.User

object UserTools {
  def create(username: String, password: String): Either[String, User] = {
    try {
      Right(UserRepo.create(username, password))
    } catch {
      case IsDuplicate() =>
        Left("Username already exists! Please choose another.")
      case e: Exception =>
        Left("Something went wrong! Please try again.")
    }
  }

  def auth(username: String, password: String): Either[String, User] = {
    try {
      UserRepo.auth(username, password) match {
        case Some(user) => Right(user)
        case None => Left("Invalid username or password!")
      }
    } catch {
      case e: Exception =>
        Left("Something went wrong! Please try again.")
    }
  }
}
