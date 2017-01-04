package edu.pw.automata.views.components

import edu.pw.automata.translations.Translations
import edu.pw.automata.views.TranslatedView
import edu.pw.automata.views.sections.Section
import io.udash.bootstrap.BootstrapStyles
import io.udash.bootstrap.navs.{UdashNav, UdashNavbar}
import io.udash.properties.seq.SeqProperty
import org.scalajs.dom.Element

import scala.concurrent.ExecutionContext.Implicits.global
import scalatags.JsDom.all._

trait Panel {
  def title: String
  def content: String
}
case class DefaultPanel(override val title: String, override val content: String) extends Panel


class Navbar extends Section with TranslatedView{

  val panels = SeqProperty[Panel](
    DefaultPanel("Arguments", "#args"),
    DefaultPanel("Definition", "#diagram-main")
  )

  lazy val navbar = UdashNavbar.inverted(
    div(BootstrapStyles.Navigation.navbarBrand)(t(Translations.fsa.fsa)).render,
    UdashNav.navbar(panels)(elemFactory = (panel) => a(panel.get.title, href := panel.get.content).render)
  )

  override def getTemplate(): Element = navbar.render
}
