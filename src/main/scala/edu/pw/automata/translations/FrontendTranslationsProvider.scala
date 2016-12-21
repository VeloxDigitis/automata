package edu.pw.automata.translations

import io.udash.i18n._
import scala.concurrent.ExecutionContext.Implicits.global

object FrontendTranslationsProvider {

  private val translations = Map(
    Lang("en") -> Bundle(BundleHash("enHash"), Map(
      "helloWorld" -> "Hello, world!"
    )),
    Lang("pl") -> Bundle(BundleHash("plHash"), Map(
      "helloWorld" -> "Witaj, świecie!"
    ))
  )

  def apply(): LocalTranslationProvider = new LocalTranslationProvider(translations)

}
