package com.ferega.todo
package lift
package rest

import model.Task

sealed trait ParsedParam[T] {
  val value: T
  def mutateTask(task: Task): Task
}

object ParsedParam {
  case class Name(value: String) extends ParsedParam[String] {
    def mutateTask(task: Task) = task.setName(value)
  }

  case class Descrption(value: String) extends ParsedParam[String] {
    def mutateTask(task: Task) = task.setDescription(value)
  }

  case class Priority(value: Option[Int]) extends ParsedParam[Option[Int]] {
    def mutateTask(task: Task) = task.setPriority(value.map(e => e: Integer).orNull)
  }
}
