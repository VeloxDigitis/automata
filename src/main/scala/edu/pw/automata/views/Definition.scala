package edu.pw.automata.views

import edu.pw.automata.DFAService
import edu.pw.automata.translations.Translations
import io.udash.bootstrap.BootstrapStyles
import io.udash.bootstrap.BootstrapStyles.Grid
import io.udash.bootstrap.utils.UdashPageHeader
import org.scalajs.dom.Element
import io.udash._
import io.udash.bootstrap.alert.UdashAlert
import io.udash.bootstrap.form.UdashInputGroup

import scalatags.JsDom.all._
import scala.concurrent.ExecutionContext.Implicits.global

class Definition extends Section with TranslatedView{

  val input = Property[String]
  val notifications = div().render

  input.listen(word => {
    notifications.innerHTML = ""
    notifications.appendChild(UdashAlert.info(DFAService.dfa.move(word).toString).render)
  })

  lazy val template = div(
    UdashPageHeader(h1(t(Translations.sections.machine), small(t(Translations.sections.machineDesc)))).render,
    div(BootstrapStyles.row)(
      div(Grid.colMd4)(
        h3(t(Translations.definition.title)),
        code(bind(DFAService.Definition.definition)),
        hr,
        UdashInputGroup.input(TextInput.debounced(input).render),
        notifications
      ),
      div(Grid.colMd8)(new Diagram().getTemplate())
    )
  )

  override def getTemplate(): Element = template.render
}