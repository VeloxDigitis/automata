package edu.pw.automata.views

import edu.pw.automata.diagram.GraphService
import edu.pw.automata.translations.Translations
import io.udash.bootstrap.button.UdashButton
import org.scalajs.dom.Element

import scalatags.JsDom.all._

class Diagram extends Section with TranslatedView{

  val u = UdashButton()(">")

  u.listen{
    case _ => {
      GraphService.move()
      GraphService.repaint()
    }
  }

  lazy val template = div(
    h3(t(Translations.diagram.title)),
    u.render,
    div(id := "diagram-main")
  )

  override def getTemplate(): Element = template.render
}