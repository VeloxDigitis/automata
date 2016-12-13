import Dependencies._

name          := "Automata"
organization  := "org.singlespaced"
scalaVersion  := "2.12.1"

version       := "0.2-SNAPSHOT"

enablePlugins(ScalaJSPlugin, WorkbenchPlugin)

persistLauncher in Compile := true
persistLauncher in Test := false

skip in packageJSDependencies := false

libraryDependencies ++= deps.value
jsDependencies      ++= jsDeps.value

jsEnv in Test := new org.scalajs.jsenv.selenium.SeleniumJSEnv(org.scalajs.jsenv.selenium.Firefox())