package com.ferega.todo
package db

import scala.collection.JavaConversions._

object UserRepo {
  val repo = new model.repositories.UserRepository(locator)

  private def toUserData(user: model.User) =
    UserData(user.getUsername, user.getPasshash)

  def create(username: String, passhash: String): UserData = {
    val user = new model.User(username, passhash)
    toUserData(user.persist())
  }

  def auth(username: String, passhash: String): Option[UserData] = {
    val spec = new model.User.auth(username, passhash)
    spec.search.toList match {
      case user :: Nil => Some(toUserData(user))
      case _ => None
    }
  }
}

case class UserData(username: String, passhash: String)
