package edu.pw.automata.views

import edu.pw.automata.translations.{Translations, FrontendTranslationsProvider}
import io.udash.bootstrap.button.{ButtonStyle, UdashButton}
import org.scalajs.dom.Element
import io.udash.i18n._

import scalatags.JsDom.all._
import scala.concurrent.ExecutionContext.Implicits.global

class Arguments extends Section with TranslatedView{

  lazy val btn = UdashButton(ButtonStyle.Primary)(translatedDynamic(Translations.helloWorld)(_.apply()))

  btn.listen { case _ =>
    if(Translations.lang.get.lang == "pl")
      Translations.lang.set(Lang("en"))
    else
      Translations.lang.set(Lang("pl"))
  }

  lazy val template = div(
    btn.render
  )

  override def getTemplate(): Element = template.render
}