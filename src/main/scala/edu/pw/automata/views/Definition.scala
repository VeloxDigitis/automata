package edu.pw.automata.views

import edu.pw.automata.DFAService
import edu.pw.automata.translations.Translations
import io.udash.bootstrap.BootstrapStyles
import io.udash.bootstrap.BootstrapStyles.Grid
import io.udash.bootstrap.utils.UdashPageHeader
import org.scalajs.dom.Element

import io.udash._
import scalatags.JsDom.all._

class Definition extends Section with TranslatedView{

  lazy val template = div(
    UdashPageHeader(h1(t(Translations.sections.machine), small(t(Translations.sections.machineDesc)))).render,
    div(BootstrapStyles.row)(
      div(Grid.colMd4)(
        h3(t(Translations.definition.title)),
        code(bind(DFAService.Definition.definition)),
        hr
      ),
      div(Grid.colMd8)(
        h3(t(Translations.diagram.title))
      )
    )
  )

  override def getTemplate(): Element = template.render
}