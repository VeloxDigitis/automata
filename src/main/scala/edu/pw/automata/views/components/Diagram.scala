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

  dom.window.setInterval(() => if(Controller.playing.get){
    Controller.move
    formattedInput.set(Controller.formattedInput)
    GraphService.repaint()
  }, 1000)

  Controller.playing.listen{
	case true => a.set("Pause")
	case false => a.set("Play")
  }
  
  play.listen{
    case _ =>
      if(Controller.playing.get)
        Controller.playing.set(false)
      else
        Controller.playing.set(true)
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