package edu.pw.automata.views

import edu.pw.automata.translations.Translations
import org.scalajs.dom.Element

import scalatags.JsDom.all._

class Diagram extends Section with TranslatedView{

  lazy val template = div(
    h3(t(Translations.diagram.title)),
    div(id := "diagram-main")
  )

  override def getTemplate(): Element = template.render
}