import sbt._
import Keys._
import Dependencies._
import com.lihaoyi.workbench.WorkbenchPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.AutoImport._

object Automata extends Build {

  lazy val project = Project("Automata", file("."))
  .enablePlugins(ScalaJSPlugin, WorkbenchPlugin)
  .settings(
    version       := "0.2-SNAPSHOT",
    scalaVersion  := "2.12.1",
    libraryDependencies ++= deps.value
  )
  .settings(
    jsEnv in Test := new org.scalajs.jsenv.selenium.SeleniumJSEnv(org.scalajs.jsenv.selenium.Firefox())
  )

}