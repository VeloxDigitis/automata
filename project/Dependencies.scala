import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt._

object Dependencies {

  val scalaJSdomVersion   = "0.9.1"
  val scalatagsVersion    = "0.6.2"
  val scalatestVersion    = "3.0.1"

  val deps = Def.setting(Seq[ModuleID](
    "org.scala-js"  %%% "scalajs-dom" 	  % scalaJSdomVersion,
    "com.lihaoyi"   %%% "scalatags"       % scalatagsVersion,
    "org.scalactic" %%% "scalactic"       % scalatestVersion,
    "org.scalatest" %%% "scalatest"       % scalatestVersion % "test"
  ))

}