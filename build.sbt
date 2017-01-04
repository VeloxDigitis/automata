import Dependencies._

name          := "Automata"
organization  := "pw.edu"
scalaVersion  := "2.11.8"

version       := "0.6-SNAPSHOT"

enablePlugins(ScalaJSPlugin, WorkbenchPlugin)

persistLauncher in Compile := true
persistLauncher in Test := false

skip in packageJSDependencies := false

libraryDependencies ++= deps.value
jsDependencies      ++= jsDeps.value

jsEnv in Test := new org.scalajs.jsenv.selenium.SeleniumJSEnv(org.scalajs.jsenv.selenium.Firefox())