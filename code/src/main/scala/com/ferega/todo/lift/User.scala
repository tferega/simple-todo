package com.ferega.todo.lift

object User {
  def logIn(username: String, password: String): Either[String, Unit] = Left("Not implemented")
  def create(username: String, password: String): Either[String, Unit] = Left("Not implemented")

  def isLoggedIn = false
}
