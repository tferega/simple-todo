package com.ferega.todo
package lift
package rest

object ParamParser {
  def parseList(list: List[String]): String =
    list match {
      case e :: Nil => e
      case _ => throw new Exception(s"Parameters cannot have multiple values!")
    }

  def parseParam(param: (String, String)): ParsedParam[_] = {
    param match {
      case ("name", raw)              => ParsedParam.Name(raw)
      case ("description", raw)       => ParsedParam.Descrption(raw)
      case ("priority", "")           => ParsedParam.Priority(None)
      case ("priority", IsInt(value)) => ParsedParam.Priority(Some(value))
      case ("priority", raw)          => throw new Exception(s"Priority could not be parsed ($raw)")
      case (key, _)                   => throw new Exception(s"Unknown key ($key)")
    }
  }

  def parse(params: Map[String, List[String]]): Either[String, IndexedSeq[ParsedParam[_]]] = {
    try {
      Right(params mapValues parseList map parseParam toIndexedSeq)
    } catch {
      case e: Exception => Left(e.getMessage)
    }
  }
}
