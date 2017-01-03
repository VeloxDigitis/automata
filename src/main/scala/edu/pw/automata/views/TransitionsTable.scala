package edu.pw.automata.views

import edu.pw.automata.DFAService
import edu.pw.automata.fsm.ExistingStatesValidator
import edu.pw.automata.translations.Translations.fsa
import io.udash.{Property, TextInput, Valid}
import io.udash.bootstrap.alert.UdashAlert
import io.udash.bootstrap.button.{ButtonStyle, UdashButton}
import io.udash.bootstrap.form.UdashInputGroup
import io.udash.bootstrap.table.UdashTable
import io.udash.properties.Invalid
import org.scalajs.dom.Element
import scala.util.{Failure, Success}

import scala.concurrent.ExecutionContext.Implicits.global
import io.udash._
import scalatags.JsDom.all._

class TransitionsTable extends TranslatedView{

  val transitions = div().render
  val columns = tr().render

  var notifications = div().render

  def reloadTable() = {
    reloadColumns()
    transitions.innerHTML = ""
    transitions.appendChild(table().render)
  }

  def reloadColumns() = {
    columns.innerHTML = ""
    columns.appendChild(th(b("#")).render)
    DFAService.Definition.alphabetNames.get.foreach(a => columns.appendChild(th(b(a.toString)).render))
  }

  def createRows(el: Seq[String]): Element = {
    val row = tr().render

    val accept = UdashButton(ButtonStyle.Link)(t(fsa.addAccepting))
    accept.listen{case _ => {DFAService.addAccepting(el(0))}}
    row.appendChild(td(el(0), accept.render).render)

    for(i <- 1 until el.size) {
      val input = Property[String](DFAService.Definition.transitions.get.filter(_.head == el.head).head(i))

      val not = UdashAlert.danger(produce(input)(v => b(v + " is invalid!").render)).render

      input.addValidator(new ExistingStatesValidator)

      def setTransition() = input.isValid.onComplete {
        case Success(Valid) => {
          DFAService.setTransition(el(0), DFAService.Definition.alphabetNames.get(i - 1), input.get)
          try{notifications.removeChild(not)} catch {case _: Exception => ()}
        }
        case Success(Invalid(_)) => notifications.appendChild(not)
        case Failure(_) => println("Something gone wrong")
      }

      setTransition()
      input.listen{_ => setTransition()}

      row.appendChild(td(UdashInputGroup.input(TextInput.debounced(input).render)).render)
    }

    row
  }

  def table() = UdashTable(Property(true), Property(true), Property(true), Property(true))(DFAService.Definition.transitions)(
    headerFactory = Some(() => columns),
    rowFactory = el => createRows(el.get)
  )

}
