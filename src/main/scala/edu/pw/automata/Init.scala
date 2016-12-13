package edu.pw.automata

import edu.pw.automata.graph.GraphService
import org.scalajs.dom._
import org.scalajs.jquery._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scalatags.JsDom.all._

@JSExport
object Init extends js.JSApp{

  @JSExport
  def main(): Unit = {

    val app = document.getElementById("application")

	  app.appendChild(
      h1("Hello, world!").render
    )

    jQuery(document)

    jQuery(document).ready{
      GraphService.go()
    }

  }
}
