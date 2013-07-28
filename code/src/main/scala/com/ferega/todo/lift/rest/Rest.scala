package com.ferega.todo
package lift
package rest

import model.Task

import net.liftweb.common.Full
import net.liftweb.http.auth.{ AuthRole, HttpBasicAuthentication, userRoles }
import net.liftweb.http._
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
    case req @ Req("rest" :: "list" :: Nil, RespType(resp), GetRequest) =>
      TaskTools.getForCurrentUser match {
        case Right(taskList) => resp.toResponse(taskList)
        case Left(message)   => InternalServerErrorResponse()
      }

    case req @ Req("rest" :: "task" :: id :: Nil, RespType(resp), GetRequest) =>
      TaskTools.getForCurrentUser(id) match {
        case Right(Some(task)) => resp.toResponse(task)
        case Right(None)       => NotFoundResponse()
        case Left(message)     => InternalServerErrorResponse()
      }

    case req @ Req("rest" :: "task" :: id :: Nil, "", PutRequest) =>
      (ParamParser.parse(req.params), TaskTools.getForCurrentUser(id)) match {
        case (Right(paramList), Right(Some(task))) =>
          paramList.foreach(_.mutateTask(task))
          TaskTools.updateForCurrentUser(task) match {
            case Right(task)   => NoContentResponse()
            case Left(message) => InternalServerErrorResponse()
          }
        case (_, Right(None))   => NotFoundResponse()
        case (Left(message), _) => InternalServerErrorResponse()
        case (_, Left(message)) => InternalServerErrorResponse()
      }
  }
}
