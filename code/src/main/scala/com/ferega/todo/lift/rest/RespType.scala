package com.ferega.todo
package lift
package rest

import model.Task

import net.liftweb.http._
import net.liftweb.json.JsonAST.JValue
import net.liftweb.json.JsonDSL._

import scala.xml.Node

sealed trait RespType {
  def toResponse(task: Task): LiftResponse
  def toResponse(taskList: IndexedSeq[Task]): LiftResponse
}

object RespType {
  case object JsonResp extends RespType {
    def toResponse(task: Task) =
      JsonResponse(taskToJson(task))
    def toResponse(taskList: IndexedSeq[Task]) =
      JsonResponse("task-list" -> (taskList map taskToJson))
  }

  case object TextResp extends RespType {
    def toResponse(task: Task) =
      PlainTextResponse(taskToText(task))
    def toResponse(taskList: IndexedSeq[Task]) =
      PlainTextResponse(taskList map taskToText mkString("\n"))
  }

  case object XmlResp  extends RespType {
    def toResponse(task: Task) =
      XmlResponse(taskToXml(task))
    def toResponse(taskList: IndexedSeq[Task]) =
      XmlResponse(
        <task-list>
          { taskList map taskToXml }
        </task-list>)
  }

  def unapply(suffix: String): Option[RespType] =
    suffix match {
      case ""     => Some(TextResp)
      case "txt"  => Some(TextResp)
      case "json" => Some(JsonResp)
      case "xml"  => Some(XmlResp)
      case _      => None
    }

  private def taskToText(task: Task): String =
    s"ID: ${ task.getID }\n" +
    s"NAME: ${ task.getName }\n" +
    s"DESCRIPTION: ${ task.getDescription }\n" +
    s"PRIORITY: ${ task.getPriority.opt.pretty }\n"
  private def taskToJson(task: Task): JValue =
    ("task" ->
      ("id" -> task.getID) ~
      ("name" -> task.getName) ~
      ("description" -> task.getDescription) ~
      ("priority" -> task.getPriority.opt.pretty)
    )
  private def taskToXml(task: Task): Node =
    <task>
      <id>{ task.getID }</id>
      <name>{ task.getName }</name>
      <description>{ task.getDescription }</description>
      <priority>{ task.getPriority.opt.pretty }</priority>
    </task>
}
