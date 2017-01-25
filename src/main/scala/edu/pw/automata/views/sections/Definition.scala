package edu.pw.automata.views.sections

import edu.pw.automata.DFAService
import edu.pw.automata.diagram.Controller
import edu.pw.automata.translations.Translations._
import edu.pw.automata.views.TranslatedView
import edu.pw.automata.views.components.Diagram
import io.udash._
import io.udash.bootstrap.BootstrapStyles
import io.udash.bootstrap.BootstrapStyles.Grid
import io.udash.bootstrap.alert.UdashAlert
import io.udash.bootstrap.form.UdashInputGroup
import io.udash.bootstrap.utils.UdashPageHeader
import org.scalajs.dom.Element

import scala.concurrent.ExecutionContext.Implicits.global
import scalatags.JsDom.all._

class Definition extends Section with TranslatedView{

  val input = Property[String]
  val notifications = div().render

  def chooseNotification(word: String) = {
    val stateO = DFAService.dfa.move(word)
    if(stateO.isDefined)
      if(DFAService.dfa.isAccepting(stateO.get))
        UdashAlert.success(t(fsa.accepting.yes)).render
      else
        UdashAlert.warning(t(fsa.accepting.no)).render
    else
      UdashAlert.warning(t(fsa.accepting.error)).render
  }

  input.listen(word => {
    notifications.innerHTML = ""
    Controller.setInput(word)
    notifications appendChild chooseNotification(word)
  })

  lazy val template = div(
    UdashPageHeader(h1(t(sections.machine), small(t(sections.machineDesc)))).render,
    div(BootstrapStyles.row)(
      div(Grid.colMd4)(
        h3(t(definition.title)),
        code(bind(DFAService.Definition.definition)),
        hr,
        UdashInputGroup.input(TextInput.debounced(input).render),
        notifications
      ),
      div(Grid.colMd8)(new Diagram(this).getTemplate())
    )
  )

  override def getTemplate(): Element = template.render
}