package edu.pw.automata.views

import edu.pw.automata.translations.Translations
import io.udash.bootstrap.BootstrapStyles
import io.udash.bootstrap.BootstrapStyles.Grid
import io.udash.bootstrap.button.{UdashButton, UdashButtonGroup}
import io.udash.bootstrap.utils.UdashPageHeader
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

  lazy val template = div(
    header,
    div(BootstrapStyles.row)(
      div(Grid.colMd4)(
        div(BootstrapStyles.row)(
          div(Grid.colMd6)(
            h3(t(Translations.fsa.states))
          ),
          div(Grid.colMd6)(
            h3(t(Translations.fsa.alphabet))
          )
        )
      ),
      div(Grid.colMd8)(
        h3(t(Translations.fsa.transitions))
      )
    )
  )

  override def getTemplate(): Element = template.render
}