package edu.pw.automata.views

import edu.pw.automata.diagram.{Controller, GraphService}
import edu.pw.automata.translations.Translations
import io.udash.bootstrap.button.{UdashButton, UdashButtonGroup}
import io.udash.properties.single.Property
import io.udash._
import org.scalajs.dom
import org.scalajs.dom.Element

import scalatags.JsDom.all._
import scala.concurrent.ExecutionContext.Implicits.global

class Diagram extends Section with TranslatedView{

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
      GraphService.repaint()
    }
  }

  lazy val template = div(
    h3(t(Translations.diagram.title)),
    UdashButtonGroup()(
      play.render,
      next.render
    ).render,
    div(id := "diagram-main", height := 480)
  )

  override def getTemplate(): Element = template.render
}