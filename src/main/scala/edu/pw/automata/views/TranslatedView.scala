package edu.pw.automata.views

import edu.pw.automata.translations._
import io.udash.i18n.TranslationProvider

trait TranslatedView {

  implicit val translationProvider: TranslationProvider = FrontendTranslationsProvider()
  implicit val lang = Translations.lang

}
