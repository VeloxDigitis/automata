package edu.pw.automata.views

import edu.pw.automata.DFAService
import edu.pw.automata.fsm.ExistingStatesValidator
import edu.pw.automata.translations.Translations._
import io.udash.{produce, _}
import io.udash.bootstrap.BootstrapStyles
import io.udash.bootstrap.BootstrapStyles.Grid
import io.udash.bootstrap.alert.UdashAlert
import io.udash.bootstrap.button.{ButtonStyle, UdashButton, UdashButtonGroup}
import io.udash.bootstrap.form.UdashInputGroup
import io.udash.bootstrap.table.UdashTable
import io.udash.bootstrap.utils.{UdashListGroup, UdashPageHeader}
import org.scalajs.dom.Element

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scalatags.JsDom.all._

class Arguments extends Section with TranslatedView{

  lazy val demoBtn = UdashButton(disabled = Property(true))(t(sections.example))

  demoBtn.listen{case e => {
    DFAService.loadDemo()
    DFAService.reload()
  }}

  lazy val header = div()(
    UdashPageHeader(h1(t(sections.args), small(t(sections.argsDesc)))).render,
    UdashButtonGroup()(
      UdashButton()(t(sections.load)).render,
      UdashButton()(t(sections.save)).render,
      demoBtn.render
    ).render
  )

  val transitions = div().render
  val columns = tr(th(b("#"))).render

  var notifications = div().render

  lazy val currentStateName = Property[String]("")
  lazy val addStateBtn = UdashButton(ButtonStyle.Primary, block = true)(t(add))

  addStateBtn.listen{case _ => {
    val c = currentStateName.get
    DFAService.addState(if(c == "") None else Some(c))
    reloadTable()

    currentStateName.set("")
  }}

  lazy val currentSymbolName = Property[String]("")
  lazy val addSymbolBtn = UdashButton(ButtonStyle.Primary, block = true)(t(add))

  addSymbolBtn.listen{case _ => {
    val c = currentSymbolName.get

    DFAService.addSymbol(if(c == "") None else Some(c))
    columns.appendChild(th(b(DFAService.Definition.alphabetNames.get.last.toString)).render)
    reloadTable()

    currentSymbolName.set("")
  }}

  val starting = Property[String]
  val sNotifications = div().render

  starting.listen(e => {
    sNotifications.innerHTML = ""
    if(!DFAService.setStarting(e))
      sNotifications.appendChild(UdashAlert.danger(e + " is invalid!").render)
  })

  def reloadTable() = {
    transitions.innerHTML = ""
    transitions.appendChild(table().render)
  }

  def createRows(el: Seq[String]): Element = {
    val row = tr().render

    val accept = UdashButton(ButtonStyle.Link)(t(fsa.addAccepting))
    accept.listen{case _ => {DFAService.addAccepting(el(0))}}
    row.appendChild(td(el(0), accept.render).render)

    for(i <- 1 to el.size - 1) {
      val input = Property[String]

      val not = UdashAlert.danger(produce(input)(v => b(v + " is invalid!").render)).render

      input.addValidator(new ExistingStatesValidator)
      input.listen{e => input.isValid.onComplete {

          case Success(Valid) => {
            DFAService.setTransition(el(0), DFAService.Definition.alphabetNames.get(i - 1), e)
            notifications.removeChild(not)
          }

          case Success(Invalid(_)) => notifications.appendChild(not)
          case Failure(_) => println("Something gone wrong")
        }
      }

      row.appendChild(td(UdashInputGroup.input(TextInput.debounced(input).render)).render)
    }

    row
  }

  def table() = UdashTable(Property(true), Property(true), Property(true), Property(true))(DFAService.Definition.transitions)(
    headerFactory = Some(() => columns),
    rowFactory = el => createRows(el.get)
  )

  lazy val template = div(
    header,
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
        transitions,
        notifications
      )
    )
  )

  override def getTemplate(): Element = template.render
}