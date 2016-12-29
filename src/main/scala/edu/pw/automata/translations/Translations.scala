package edu.pw.automata.translations

import io.udash.i18n._
import TranslationKey._
import io.udash.properties.single.Property
import scala.concurrent.ExecutionContext.Implicits.global

object Translations {

  val lang = Property(Lang("pl"))

  val add = key("add")

  val isWrong = key("isWrong")

  object sections {
    val args = key("args")
    val argsDesc = key("argsDesc")

    val load = key("load")
    val save = key("save")
    val example = key("example")

    val machine = key("machine")
    val machineDesc = key("machineDesc")

  }

  object fsa {

    val fsa = key("fsa")
    val states = key("states")
    val alphabet = key("alphabet")
    val transitions = key("transitions")
    val addAccepting = key("addAccepting")
    val removeAccepting = key("removeAccepting")
    val starting = key("starting")

    object accepting {
      val yes = key("aYes")
      val no = key("aNo")
      val error = key("aError")
    }
  }

  object definition {
    val title = key("defTitle")
  }

  object diagram {
    val title = key("diagramTitle")
  }

}
