import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt._

object Dependencies {

  val scalaJSdomVersion   = "0.9.1"
  val scalatagsVersion    = "0.6.2"
  val scaladgetVersion    = "0.8.1"
  val scalatestVersion    = "3.0.1"

  val deps = Def.setting(Seq[ModuleID](
    "org.scala-js"  %%% "scalajs-dom" 	  % scalaJSdomVersion,
    "be.doeraene"   %%% "scalajs-jquery"  % scalaJSdomVersion,
    "com.lihaoyi"   %%% "scalatags"       % scalatagsVersion,
    "fr.iscpif"     %%% "scaladget"       % scaladgetVersion,
    "org.scalactic" %%% "scalactic"       % scalatestVersion,
    "org.scalatest" %%% "scalatest"       % scalatestVersion % "test"
  ))

  val jsDeps = Def.setting(Seq[org.scalajs.sbtplugin.JSModuleID](
    "org.webjars" % "jquery" % "1.10.2" / "jquery.js"
  ))

}