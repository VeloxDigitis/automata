package edu.pw.automata.views

import edu.pw.automata.translations._
import io.udash.i18n.{TranslationKey0, TranslationProvider, translatedDynamic}
import scala.concurrent.ExecutionContext.Implicits.global

trait TranslatedView {

  implicit val translationProvider: TranslationProvider = FrontendTranslationsProvider()
  implicit val lang = Translations.lang

  def t(t: TranslationKey0) = {
    translatedDynamic(t)(_.apply())
  }

}
