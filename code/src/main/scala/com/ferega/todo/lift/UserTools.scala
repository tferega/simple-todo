package com.ferega.todo
package lift

import db.{ IsDuplicate, UserRepo }
import model.User

object UserTools {
  def create(username: String, password: String): Either[String, User] = {
    try {
      val salt = Security.createSalt
      val passhash = Security.createHash(password, salt)
      val newUserFut = UserRepo.create(username, salt, passhash)
      val newUser = newUserFut.get
      Right(newUser)
    } catch {
      case IsDuplicate() =>
        Left("Username already exists! Please choose another.")
      case e: Exception =>
        Left("Something went wrong! Please try again.")
    }
  }

  def auth(username: String, password: String): Either[String, User] = {
    try {
      val foundUserOptFut = UserRepo.find(username)
      val foundUserOpt = foundUserOptFut.get
      foundUserOpt match {
        case Some(foundUser) =>
          Security.validate(password, foundUser.getSalt, foundUser.getPasshash) match {
            case true  => Right(foundUser)
            case false => Left("Invalid username or password!")
          }
        case None =>
          Left("Invalid username or password!")
      }
    } catch {
      case e: Exception =>
        Left("Something went wrong! Please try again.")
    }
  }
}
