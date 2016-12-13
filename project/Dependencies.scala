import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt._

object Dependencies {

  val scalaJSdomVersion   = "0.9.1"
  val scalatagsVersion    = "0.6.2"
  val scalajsD3Version    = "0.3.4"
  val scalatestVersion    = "3.0.1"

  val deps = Def.setting(Seq[ModuleID](
    "org.scala-js"  %%% "scalajs-dom" 	  % scalaJSdomVersion,
    "be.doeraene"   %%% "scalajs-jquery"  % scalaJSdomVersion,
    "com.lihaoyi"   %%% "scalatags"       % scalatagsVersion,
    "org.singlespaced" %%% "scalajs-d3"   % scalajsD3Version,
    "org.scalactic" %%% "scalactic"       % scalatestVersion,
    "org.scalatest" %%% "scalatest"       % scalatestVersion % "test"
  ))

  val jsDeps = Def.setting(Seq[org.scalajs.sbtplugin.JSModuleID](
    "org.webjars" % "jquery" % "1.10.2" / "jquery.js"
  ))

}