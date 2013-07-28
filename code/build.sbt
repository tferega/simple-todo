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
  "com.dslplatform"          % "dsl-client-http"        % "0.3.8",
  "net.liftweb"             %% "lift-webkit"            % "2.5" % "compile",
  "net.liftweb"             %% "lift-mapper"            % "2.5" % "compile",
  "net.liftmodules"         %% "lift-jquery-module_2.5" % "2.3",
  "org.eclipse.jetty"        % "jetty-webapp"           % "8.1.7.v20120910"     % "container,test",
  "org.eclipse.jetty.orbit"  % "javax.servlet"          % "3.0.0.v201112011016" % "container,test" artifacts Artifact("javax.servlet", "jar", "jar"),
  "ch.qos.logback"           % "logback-classic"        % "1.0.13",
  "org.scala-lang" % "scala-library"  % "2.10.2")

seq(com.github.siasia.WebPlugin.webSettings :_*)

unmanagedSourceDirectories in Compile <<= (scalaSource in Compile) ( sS =>
    sS :: file("src/generated/java") :: Nil
)

unmanagedSourceDirectories in Test := Nil

initialCommands in console := """import com.ferega.todo._"""
