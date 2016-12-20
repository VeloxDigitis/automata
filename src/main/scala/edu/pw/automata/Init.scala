package edu.pw.automata

import edu.pw.automata.views.Arguments
import org.scalajs.dom._
import org.scalajs.jquery._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

@JSExport
object Init extends js.JSApp{

  @JSExport
  def main(): Unit = {

    val app = document.getElementById("application")

    jQuery(document).ready{
      app.appendChild(Arguments.getTemplate())
    }

  }
}
