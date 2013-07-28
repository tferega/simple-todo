package com.ferega.todo
package lift

import model.User

import net.liftweb.http.RequestVar

object Request {
  private object current extends RequestVar[Option[User]](None)

  def create(user: User) {
    current.remove
    current(Some(user))
  }

  def destroy() = {
    current.remove
  }

  def isDefined = current.isDefined
  def isEmpty   = current.isEmpty

  def get = current.get.getOrElse(throw new Exception("Request not defined"))
}