package edu.pw.automata.views

import edu.pw.automata.DFAService
import edu.pw.automata.diagram.Controller
import edu.pw.automata.translations.Translations._
import io.udash._
import io.udash.bootstrap.BootstrapStyles
import io.udash.bootstrap.BootstrapStyles.Grid
import io.udash.bootstrap.alert.UdashAlert
import io.udash.bootstrap.button.{ButtonStyle, UdashButton}
import io.udash.bootstrap.form.UdashInputGroup
import io.udash.bootstrap.utils.UdashListGroup
import org.scalajs.dom.Element

import scala.concurrent.ExecutionContext.Implicits.global
import scalatags.JsDom.all._

class Arguments extends Section with TranslatedView{

  val header = new ArgumentsHeader(this)

  lazy val currentStateName = Property[String]("")
  lazy val addStateBtn = UdashButton(ButtonStyle.Primary, block = true)(t(add))

  addStateBtn.listen{case _ => {
    val c = currentStateName.get
    DFAService.addState(if(c == "") None else Some(c))
    table.reloadTable()

    currentStateName.set("")
  }}

  lazy val currentSymbolName = Property[String]("")
  lazy val addSymbolBtn = UdashButton(ButtonStyle.Primary, block = true)(t(add))

  addSymbolBtn.listen{case _ => {
    val c = currentSymbolName.get

    DFAService.addSymbol(if(c == "") None else Some(c))
    table.columns.appendChild(th(b(DFAService.Definition.alphabetNames.get.last.toString)).render)
    table.reloadTable()

    currentSymbolName.set("")
  }}

  val starting = Property[String]
  val sNotifications = div().render

  starting.listen(e => {
    sNotifications.innerHTML = ""
    if(!DFAService.setStarting(e))
      sNotifications.appendChild(UdashAlert.danger(e + " is invalid!").render)
    if(Controller.current.isEmpty)
      Controller.current = DFAService.dfa.getQ0
  })

  val table = new TransitionsTable()

  lazy val template = div(
    header.getTemplate(),
    div(BootstrapStyles.row)(
      div(Grid.colMd4)(
        div(BootstrapStyles.row)(
          div(Grid.colMd6)(
            h3(t(fsa.states)),
            UdashListGroup(DFAService.Definition.stateNames)((t) => li(bind(t)).render).render,
            UdashInputGroup.input(TextInput.debounced(currentStateName).render),
            addStateBtn.render
          ),
          div(Grid.colMd6)(
            h3(t(fsa.alphabet)),
            UdashListGroup(DFAService.Definition.alphabetNames)((t) => li(bind(t)).render).render,
            UdashInputGroup.input(TextInput.debounced(currentSymbolName).render),
            addSymbolBtn.render
          )
        ),
        hr,
        h4(t(fsa.starting)),
        UdashInputGroup.input(TextInput.debounced(starting).render),
        sNotifications
      ),
      div(Grid.colMd8)(
        h3(t(fsa.transitions)),
        table.transitions,
        table.notifications
      )
    )
  )

  override def getTemplate(): Element = template.render
}