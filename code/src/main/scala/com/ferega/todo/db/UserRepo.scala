package com.ferega.todo
package db

import model.User

import scala.collection.JavaConversions._

object UserRepo {
  val repo = new model.repositories.UserRepository(locator)

  def create(username: String, passhash: String): User = {
    val user = new model.User(username, passhash)
    user.persist()
  }

  def auth(username: String, passhash: String): Option[User] = {
    val spec = new model.User.auth(username, passhash)
    spec.search.toList match {
      case user :: Nil => Some(user)
      case _ => None
    }
  }
}
