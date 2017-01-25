package edu.pw.automata.views.components

import edu.pw.automata.DFAService
import edu.pw.automata.fsm.DFA
import edu.pw.automata.translations.Translations.sections._
import edu.pw.automata.views.TranslatedView
import edu.pw.automata.views.sections.{Arguments, Section}
import io.udash.bootstrap.button.{UdashButton, UdashButtonGroup}
import io.udash.bootstrap.utils.UdashPageHeader
import org.scalajs.dom.Element

import scala.concurrent.ExecutionContext.Implicits.global
import scalatags.JsDom.all._

class ArgumentsHeader(arguments: Arguments) extends Section with TranslatedView {

  lazy val demoBtn = UdashButton()(t(example))

  demoBtn.listen{case _ => {
    DFAService.loadDemo()

    DFAService.reloadT()
    arguments.table.reloadTable()
    arguments.starting.set(DFAService.dfa.getQ0.getOrElse("").toString) //TODO
  }}

  lazy val resetBtn = UdashButton()(t(reset))

  resetBtn.listen{case _ => {
    DFAService.dfa = DFA.empty()
    DFAService.reloadT()
    arguments.table.reloadTable()
    arguments.starting.set("")
  }}

  lazy val header = div()(
    UdashPageHeader(h1(t(args), small(t(argsDesc)))).render,
    UdashButtonGroup()(
      UdashButton()(t(load)).render,
      UdashButton()(t(save)).render,
      demoBtn.render,
      resetBtn.render
    ).render
  )

  override def getTemplate(): Element = header.render
}
