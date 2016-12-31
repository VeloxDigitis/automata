package edu.pw.automata.translations

import io.udash.i18n._
import scala.concurrent.ExecutionContext.Implicits.global

object FrontendTranslationsProvider {

  private val translations = Map(
    Lang("en") -> Bundle(BundleHash("enHash"), Map(
      "changeLang" -> "Change language",
      "add" -> "Add",
      "isWrong" -> "is invalid",

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
      "starting" -> "Starting",

      "defTitle" -> "Definition",
      "diagramTitle" -> "Diagram",

      "addAccepting" -> "Accepting",
      "removeAccepting" -> "Remove",

      "aYes" -> "Accepts",
      "aNo" -> "Doesn't accept",
      "aError" -> "Error"
    )),
    Lang("pl") -> Bundle(BundleHash("plHash"), Map(
      "changeLang" -> "Zmień język",
      "add" -> "Dodaj",
      "isWrong" -> "zawiera blad",

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
      "starting" -> "Stan startowy",

      "defTitle" -> "Definicja",
      "diagramTitle" -> "Diagram",

      "addAccepting" -> "Kończący",
      "removeAccepting" -> "Usuń",

      "aYes" -> "Akceptuje",
      "aNo" -> "Nie akceptuje",
      "aError" -> "Błąd"
    ))
  )

  def apply(): LocalTranslationProvider = new LocalTranslationProvider(translations)

}
