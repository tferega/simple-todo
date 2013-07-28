package com.ferega.todo
package lift

import model.User

import net.liftweb.http.{ S, SessionVar }

object Session {
  private object current extends SessionVar[Option[User]](None)

  def create(user: User) {
    current.remove
    current(Some(user))
  }

  def destroy() = {
    current.remove
    S.session.foreach(_.destroySession)
  }

  def isDefined = current.isDefined
  def isEmpty   = current.isEmpty

  def opt = current.get
  def get = current.get.getOrElse(throw new Exception("Session not defined"))
}