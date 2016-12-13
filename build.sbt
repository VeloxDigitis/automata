import Dependencies.deps

name          := "Automata"
organization  := "org.singlespaced"
scalaVersion  := "2.12.1"

version       := "0.2-SNAPSHOT"

enablePlugins(ScalaJSPlugin, WorkbenchPlugin)

libraryDependencies ++= deps.value

jsEnv in Test := new org.scalajs.jsenv.selenium.SeleniumJSEnv(org.scalajs.jsenv.selenium.Firefox())