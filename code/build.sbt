name := "SimpleTODO"

organization := "com.ferega"

version := "0.0.0-SNAPSHOT"

scalaVersion := "2.10.2"

autoScalaLibrary := false

scalacOptions := Seq(
    "-unchecked",
    "-deprecation",
    "-optimise",
    "-encoding", "UTF-8",
    // "-explaintypes",
    "-Xcheckinit",
    // "-Xfatal-warnings",
    "-Yclosure-elim",
    "-Ydead-code",
    // "-Yinline",
    // "-Yinline-warnings",
    "-Xmax-classfile-name", "72",
    "-Yrepl-sync",
    "-Xlint",
    "-Xverify",
    "-Ywarn-all",
    "-feature",
    "-language:postfixOps",
    "-language:implicitConversions",
    "-language:existentials")

libraryDependencies := Seq(
  "org.scala-lang" % "scala-library"  % "2.10.2")

unmanagedSourceDirectories in Compile <<= (scalaSource in Compile) ( sS =>
    sS :: file("src/generated/java") :: Nil
)

unmanagedSourceDirectories in Test := Nil
