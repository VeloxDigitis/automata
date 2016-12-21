import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt._

object Dependencies {

  val scalaJSdomVersion         = "0.9.1"
  val scalatagsVersion          = "0.6.2"
  val scaladjsBootstrapVersion  = "1.1.2"
  val scalatestVersion          = "3.0.0"

  val jqueryVersion = "1.11.1"
  val jqueryPath = s"META-INF/resources/webjars/jquery/$jqueryVersion/jquery.js"

  val deps = Def.setting(Seq[ModuleID](
    "org.scala-js"  %%% "scalajs-dom" 	  % scalaJSdomVersion,
    "be.doeraene"   %%% "scalajs-jquery"  % scalaJSdomVersion,
    "com.lihaoyi"   %%% "scalatags"       % scalatagsVersion,
    "org.scalactic" %%% "scalactic"       % scalatestVersion,
    "org.scalatest" %%% "scalatest"       % scalatestVersion % "test"
  ))

  val jsDeps = Def.setting(Seq[org.scalajs.sbtplugin.JSModuleID](
    "org.webjars" % "jquery" % jqueryVersion / jqueryPath minified jqueryPath.replace(".js", ".min.js"),
    "org.webjars" % "bootstrap" % "3.3.6" / "js/bootstrap.js" dependsOn jqueryPath minified "js/bootstrap.min.js"
  ))

}