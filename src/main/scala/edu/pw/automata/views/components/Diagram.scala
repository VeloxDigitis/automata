package edu.pw.automata.views.components

import edu.pw.automata.diagram.{Controller, GraphService}
import edu.pw.automata.translations.Translations
import edu.pw.automata.views.TranslatedView
import edu.pw.automata.views.sections.{Definition, Section}
import io.udash._
import io.udash.bootstrap.button.{UdashButton, UdashButtonGroup}
import io.udash.properties.single.Property
import org.scalajs.dom
import org.scalajs.dom.Element

import scala.concurrent.ExecutionContext.Implicits.global
import scalatags.JsDom.all._

class Diagram(definition: Definition) extends Section with TranslatedView{

  val a = Property[String]("Play")

  val stop = UdashButton()("Stop")
  val play = UdashButton()(bind(a))
  val next = UdashButton()(">")

  val formattedInput = Property[String]

  stop.listen {
    case _ => {
      Controller.reset()
      GraphService.repaint()
    }
  }

  val playing = Property[Boolean](false)

  dom.window.setInterval(() => if(playing.get){
    Controller.move
    formattedInput.set(Controller.formattedInput)
    GraphService.repaint()
  }, 1000)

  play.listen{
    case _ => {
      if(playing.get) {
        playing.set(false)
        a.set("Play")
      } else {
        playing.set(true)
        a.set("Pause")
      }
    }
  }

  next.listen{
    case _ => {
      Controller.move()
      formattedInput.set(Controller.formattedInput)
      GraphService.repaint()
    }
  }

  lazy val template = div(
    h3(t(Translations.diagram.title)),
    UdashButtonGroup()(
      play.render,
      next.render
    ).render,
    b(br()),
    bind(formattedInput),
    div(id := "diagram-main", height := 480)
  )

  override def getTemplate(): Element = template.render
}