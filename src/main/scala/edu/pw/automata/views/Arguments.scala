package edu.pw.automata.views

import edu.pw.automata.DFAService
import edu.pw.automata.translations.Translations
import io.udash._
import io.udash.bootstrap.BootstrapStyles
import io.udash.bootstrap.BootstrapStyles.Grid
import io.udash.bootstrap.button.{ButtonStyle, UdashButton, UdashButtonGroup}
import io.udash.bootstrap.dropdown.UdashDropdown
import io.udash.bootstrap.form.UdashInputGroup
import io.udash.bootstrap.table.UdashTable
import io.udash.bootstrap.utils.{UdashListGroup, UdashPageHeader}
import org.scalajs.dom.Element

import scala.concurrent.ExecutionContext.Implicits.global
import scalatags.JsDom.all._

class Arguments extends Section with TranslatedView{

  lazy val header = div()(
    UdashPageHeader(h1(t(Translations.sections.args), small(t(Translations.sections.argsDesc)))).render,
    UdashButtonGroup()(
      UdashButton()(t(Translations.sections.load)).render,
      UdashButton()(t(Translations.sections.save)).render,
      UdashButton()(t(Translations.sections.example)).render
    ).render
  )

  val transitions = div().render

  val columns = tr(th(b("#"))).render

  lazy val currentStateName = Property[String]("")
  lazy val addStateBtn = UdashButton(ButtonStyle.Primary, block = true)(t(Translations.add))

  addStateBtn.listen{case e => {
    val c = currentStateName.get
    DFAService.addState(if(c == "") None else Some(c))
    reloadTable()
    currentStateName.set("")
  }}

  lazy val currentSymbolName = Property[String]("")
  lazy val addSymbolBtn = UdashButton(ButtonStyle.Primary, block = true)(t(Translations.add))

  addSymbolBtn.listen{case e => {
    val c = currentSymbolName.get
    DFAService.addSymbol(if(c == "") None else Some(c))
    columns.appendChild(th(b(DFAService.Definition.alphabetNames.get.last.toString)).render)
    reloadTable()
    currentSymbolName.set("")
  }}

  def reloadTable() = {
    transitions.innerHTML = ""
    transitions.appendChild(table().render)
  }

  def createRows(el: Seq[String]): Element = {
    val row = tr().render
    row.appendChild(td(el(0)).render)

    for(i <- 1 to el.size - 1) {
      val input = Property[String](el(0))

      input.listen(e => DFAService.setTransition(el(0), DFAService.Definition.alphabetNames.get(i - 1), e))

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
            h3(t(Translations.fsa.states)),
            UdashListGroup(DFAService.Definition.stateNames)((t) => li(bind(t)).render).render,
            UdashInputGroup.input(TextInput.debounced(currentStateName).render),
            addStateBtn.render
          ),
          div(Grid.colMd6)(
            h3(t(Translations.fsa.alphabet)),
            UdashListGroup(DFAService.Definition.alphabetNames)((t) => li(bind(t)).render).render,
            UdashInputGroup.input(TextInput.debounced(currentSymbolName).render),
            addSymbolBtn.render
          )
        )
      ),
      div(Grid.colMd8)(
        h3(t(Translations.fsa.transitions)),
        transitions
      )
    )
  )

  //DFAService.addState("XD")

  override def getTemplate(): Element = template.render
}