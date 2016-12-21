package edu.pw.automata

import edu.pw.automata.views.Index
import org.scalajs.dom._
import org.scalajs.jquery._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

@JSExport
object Init extends js.JSApp{

  @JSExport
  def main(): Unit = jQuery(() ⇒ Index.render(document.getElementById("application")))
}
