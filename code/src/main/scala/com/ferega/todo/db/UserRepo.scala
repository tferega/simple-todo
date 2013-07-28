package com.ferega.todo
package db

import model.User
import model.repositories.UserRepository

import scala.concurrent.Future

object UserRepo {
  val repo = new UserRepository(locator)

  def create(username: String, salt: Array[Byte], passhash: Array[Byte]): Future[User] = {
    val user = new User(username, salt, passhash)
    val uriFut = insertWrap(user)
    uriFut flatMap findWrap
  }

  def find(username: String): Future[Option[User]] =
    findWrap(username).
      map(Some(_)).
      recover {
        case NotFound() => None
      }

  private def findWrap(uri: String): Future[User] =
    wrapJavaFuture { repo.find(uri) }

  private def insertWrap(user: User): Future[String] =
    wrapJavaFuture { repo.insert(user) }
}
