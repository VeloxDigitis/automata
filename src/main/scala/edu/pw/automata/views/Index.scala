package edu.pw.automata.views

import org.scalajs.dom.Element

import scala.language.postfixOps

object Index {

  def render(app: Element) = {

    app.appendChild(new Arguments().getTemplate())

  }

}
