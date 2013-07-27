package com.ferega.todo
package lift

import db.{ IsDuplicate, UserData, UserRepo }

import net.liftweb.http.{ S, SessionVar }

object UserTools {
  def create(username: String, password: String): Either[String, UserData] = {
    try {
      Right(UserRepo.create(username, password))
    } catch {
      case IsDuplicate() =>
        Left("Username already exists! Please choose another.")
      case e: Exception =>
        Left("Something went wrong! Please try again.")
    }
  }

  def auth(username: String, password: String): Either[String, UserData] = {
    try {
      UserRepo.auth(username, password) match {
        case Some(userData) => Right(userData)
        case None => Left("Invalid username or password!")
      }
    } catch {
      case e: Exception =>
        Left("Something went wrong! Please try again.")
    }
  }
}
