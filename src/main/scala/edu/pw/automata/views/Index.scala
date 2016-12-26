package edu.pw.automata.views

import org.scalajs.dom.Element

import scalatags.JsDom.all._
import scala.language.postfixOps

object Index {

  def render(app: Element) = {

    val main = div(cls := "container")(
      new Arguments().getTemplate(),
      new Definition().getTemplate()
    )

    app.appendChild(new Navbar().getTemplate())

    app.appendChild(main.render)

  }

}
