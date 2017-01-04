package edu.pw.automata.views

import edu.pw.automata.translations.Translations
import edu.pw.automata.views.components.Navbar
import edu.pw.automata.views.sections.{Arguments, Definition}
import io.udash.bootstrap.button.UdashButton
import io.udash.i18n.Lang
import org.scalajs.dom.Element

import scalatags.JsDom.all._
import scala.language.postfixOps

object Index extends TranslatedView{

  val langBtn = UdashButton()(t(Translations.changeLang))

  langBtn.listen{case _ => {
    val t = Translations.lang
    if(t.get.lang == "en")
      Translations.lang.set(Lang("pl"))
    else
      Translations.lang.set(Lang("en"))
  }}

  def render(app: Element) = {

    val main = div(cls := "container")(
      langBtn.render,
      new Arguments().getTemplate(),
      new Definition().getTemplate()
    )

    app.appendChild(new Navbar().getTemplate())

    app.appendChild(main.render)

  }

}
