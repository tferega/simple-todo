package com.ferega.todo
package lift

import db.UserData

import net.liftweb.http.{ S, SessionVar }

object Session {
  private object current extends SessionVar[Option[UserData]](None)

  def create(userData: UserData) {
    current.remove
    current(Some(userData))
  }

  def destroy() = {
    current.remove
    S.session.foreach(_.destroySession)
  }

  def isDefined = current.isDefined
  def isEmpty   = current.isEmpty

  def get = current.get.getOrElse(throw new Exception("Session not defined"))
}