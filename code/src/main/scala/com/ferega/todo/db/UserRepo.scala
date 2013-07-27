package com.ferega.todo
package db

object UserRepo {
  val repo = new model.repositories.UserRepository(locator)

  def create(username: String, passhash: String) {
    val user = new model.User(username, passhash)
    user.persist()
  }

  def find(username: String, passhash: String): Boolean = {
    val spec = new model.User.auth(username, passhash)
    spec.search.size == 1
  }
}