enablePlugins(ScalaJSPlugin, WorkbenchPlugin)

name := "Automata"

version := "0.1-SNAPSHOT"

scalaVersion := "2.12.1"

logBuffered in Test := false
jsDependencies += RuntimeDOM % "test"

libraryDependencies ++= Seq(
  "org.scala-js"  %%% "scalajs-dom" 	% "0.9.1",
  "com.lihaoyi"   %%% "scalatags"  % "0.6.2",
  "org.scalactic" %%% "scalactic"     % "3.0.1",
  "org.scalatest" %%% "scalatest" 		% "3.0.1" % "test"
)

jsEnv in Test := new org.scalajs.jsenv.selenium.SeleniumJSEnv(org.scalajs.jsenv.selenium.Firefox())
 