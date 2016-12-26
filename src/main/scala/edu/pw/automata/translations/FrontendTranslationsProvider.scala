package edu.pw.automata.translations

import io.udash.i18n._
import scala.concurrent.ExecutionContext.Implicits.global

object FrontendTranslationsProvider {

  private val translations = Map(
    Lang("en") -> Bundle(BundleHash("enHash"), Map(
      "args" -> "Arguments",
      "argsDesc" -> "Setup your own machine",

      "machine" -> "Machine",
      "machineDesc" -> "Definition and diagram",

      "load" -> "Load from disc",
      "save" -> "Save",
      "example" -> "Load example",

      "fsa" -> "Finite state automata",
      "states" -> "States",
      "alphabet" -> "Alphabet",
      "transitions" -> "Transitions",

      "defTitle" -> "Definition",
      "diagramTitle" -> "Diagram"
    )),
    Lang("pl") -> Bundle(BundleHash("plHash"), Map(
      "args" -> "Argumenty",
      "argsDesc" -> "Skonfiguruj własną maszynę",

      "machine" -> "Automat",
      "machineDesc" -> "Definicja oraz diagram stanów",

      "load" -> "Wczytaj z dysku",
      "save" -> "Zapisz na dysk",
      "example" -> "Wczytaj demo",

      "fsa" -> "Automat skończony",
      "states" -> "Stany",
      "alphabet" -> "Alfabet",
      "transitions" -> "Przejścia",

      "defTitle" -> "Definicja",
      "diagramTitle" -> "Diagram"
    ))
  )

  def apply(): LocalTranslationProvider = new LocalTranslationProvider(translations)

}
