package edu.pw.automata.translations

import io.udash.i18n._
import TranslationKey._
import io.udash.properties.single.Property
import scala.concurrent.ExecutionContext.Implicits.global

object Translations {

  val lang = Property(Lang("pl"))

  val helloWorld = key("helloWorld")

}
