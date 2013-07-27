package com.ferega.todo
package lift

import db.{ NotFound, UserRepo }

import java.io.IOException

import net.liftweb.common.{ Box, Empty, Full }
import net.liftweb.http.SessionVar

import scala.concurrent.Await

object User {
  private object currentUser extends SessionVar[Box[String]](Empty)

  def logIn(username: String, password: String): Either[String, Unit] = {
    val userFut = UserRepo.find(username)
    try {
      val user = Await.result(userFut, dbTimeout)
      logInUser(user)
      Right(Unit)
    } catch {
      case NotFound() =>
        Left("Invalid username or password")
      case e: Exception =>
        e.printStackTrace()
        Left("Something went wrong. Please try again")
    }
  }

  def create(username: String, password: String): Either[String, Unit] = Left("Not implemented")

  def isLoggedIn = currentUser.isDefined

  private def logInUser(user: model.User) {
    currentUser.remove
    currentUser(Full(user.getUsername()))
  }
}
