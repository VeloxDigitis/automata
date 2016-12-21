package edu.pw.automata.views

import org.scalajs.dom.Element

import scalatags.JsDom.all._

class Arguments extends Section{

  lazy val template = h1(
    "Hello world"
  )

  override def getTemplate(): Element = template.render
}