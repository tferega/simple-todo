package com.ferega.todo
package db

import scala.concurrent.Future

object UserRepo {
  val repo = new model.repositories.UserRepository(locator)

  def find(username: String) = wrapJavaFuture {
    repo.find(username)
  }
}