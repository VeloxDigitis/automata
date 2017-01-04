import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt._

object Dependencies {

  val scalaJSdomVersion         = "0.9.1"
  val scaladjsBootstrapVersion  = "1.1.2"
  val scalatestVersion          = "3.0.0"

  val udashVersion = "0.4.0"
  val udashJQueryVersion = "1.0.0"

  val deps = Def.setting(Seq[ModuleID](
    "org.scala-js"  %%% "scalajs-dom" 	      % scalaJSdomVersion,
    "io.udash"      %%% "udash-core-frontend" % udashVersion,
    "io.udash"      %%% "udash-jquery"        % udashJQueryVersion,
    "io.udash"      %%% "udash-bootstrap"     % udashVersion,
    "io.udash"      %%% "udash-i18n-frontend" % udashVersion,
    "org.scalactic" %%% "scalactic"           % scalatestVersion,
    "org.scalatest" %%% "scalatest"           % scalatestVersion % "test"
  ))

  val jsDeps = Def.setting(Seq[org.scalajs.sbtplugin.JSModuleID](
  ))

}