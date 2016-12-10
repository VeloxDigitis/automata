enablePlugins(ScalaJSPlugin, WorkbenchPlugin)

name := "Automata"

version := "0.1-SNAPSHOT"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "org.scala-js"  %%% "scalajs-dom" 	% "0.9.1",
  "com.lihaoyi"		% "scalatags_2.11" 	% "0.6.2"
)
