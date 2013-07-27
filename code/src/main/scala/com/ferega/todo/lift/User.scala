package com.ferega.todo
package lift

import db.{ IsDuplicate, UserRepo }

import java.io.IOException

import net.liftweb.http.{ S, SessionVar }

import scala.concurrent.Await

object User {
  private object currentUser extends SessionVar[Option[String]](None)

  def create(username: String, password: String): Either[String, Unit] = {
    try {
      UserRepo.create(username, password)
      logInUser(username)
      Right(Unit)
    } catch {
      case IsDuplicate() =>
        Left("Username already exists! Please choose another.")
      case e: Exception =>
        Left("Something went wrong! Please try again.")
    }
  }

  def logIn(username: String, password: String): Either[String, Unit] = {
    try {
      val isFound = UserRepo.find(username, password)
      if (isFound) {
        logInUser(username)
        Right(Unit)
      } else {
        Left("Invalid username or password!")
      }
    } catch {
      case e: Exception =>
        Left("Something went wrong! Please try again.")
    }
  }

  def logOut() = {
    currentUser.remove
    S.session.foreach(_.destroySession)
  }

  def isLoggedIn  = currentUser.isDefined
  def notLoggedIn = !isLoggedIn

  def name = currentUser.get

  private def logInUser(user: String) {
    currentUser.remove
    currentUser(Some(user))
  }
}
