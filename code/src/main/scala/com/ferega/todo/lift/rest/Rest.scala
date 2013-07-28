package com.ferega.todo
package lift
package rest

import net.liftweb.common.Full
import net.liftweb.http.auth.{ AuthRole, HttpBasicAuthentication, userRoles }
import net.liftweb.http.{ LiftRules, PlainTextResponse, Req }
import net.liftweb.http.rest.RestHelper

object Rest extends RestHelper {
  private val Role = AuthRole("auth")

  def protection: LiftRules.HttpAuthProtectedResourcePF = {
    case Req("rest" :: _, _, _) => Full(Role)
  }

  def authentication = HttpBasicAuthentication("Authentication needed!") {
    case (username, password, req) => {
      UserTools.auth(username, password) match {
        case Right(user) =>
          userRoles(Role :: Nil)
          Request.create(user)
          true
        case Left(message) =>
          false
      }
    }
  }

  serve {
    case Req("rest" :: _, _, _) =>
      val user = Request.get
      PlainTextResponse(s"Boo, ${ user.getUsername() }!")
  }
}