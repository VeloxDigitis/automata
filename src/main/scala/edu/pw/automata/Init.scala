package edu.pw.automata

import org.scalajs.dom.html
import scala.scalajs.js.annotation.JSExport
import scalatags.JsDom.all._

@JSExport
object Init {

  @JSExport
  def main(app: html.Div): Unit = {
    
	  app.appendChild(
      h1("Hello, world!").render
    )
  }
}
