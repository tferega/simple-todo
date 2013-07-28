package com.ferega.todo
package db

import java.util.concurrent.{ Future => JFuture }

import model.repositories.TaskRepository
import model.{ Task, User }

import scala.collection.JavaConversions._
import scala.concurrent.Future

object TaskRepo {
  val repo = new TaskRepository(locator)

  def create(user: User, name: String, description: String, priority: Option[Int]): Future[Task] = {
    val task = new Task(user, name, description, priority.map(e => e: Integer).orNull)
    val uriFut = insertWrap(task)
    uriFut flatMap findWrap
  }

  def findByUser(user: User): Future[IndexedSeq[Task]] = {
    val spec = new Task.findByUser(user.getUsername)
    Future {
      val taskList = spec.search.toIndexedSeq
      taskList sortBy taskSort
    }
  }

  def find(id: String): Future[Option[Task]] =
    findWrap(id).
      map(Some(_)).
      recover {
        case NotFound() => None
      }

  def update(mutatedTask: Task): Future[Task] =
    updateWrap(mutatedTask)

  def delete(task: Task): Future[Task] =
    deleteWrap(task)


  private def findWrap(uri: String): Future[Task] =
    wrapJavaFuture { repo.find(uri) }

  private def insertWrap(task: Task): Future[String] =
    wrapJavaFuture { repo.insert(task) }

  private def updateWrap(mutatedTask: Task): Future[Task] =
      wrapJavaFuture { repo.update(mutatedTask) }

  private def deleteWrap(task: Task): Future[Task] =
    wrapJavaFuture { repo.delete(task).asInstanceOf[JFuture[Task]] }

  private def taskSort(task: Task): (Int, Int) = {
    (task.getPriority.opt.getOrElse(Int.MaxValue), task.getID())
  }
}
