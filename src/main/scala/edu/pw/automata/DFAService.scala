package edu.pw.automata

import edu.pw.automata.fsm.{DFA, State}
import io.udash.properties.seq.SeqProperty
import io.udash.properties.single.Property

import scala.concurrent.ExecutionContext.Implicits.global

object DFAService {

  var dfa = DFA.empty()

  def addState(name: String) = {
    dfa = dfa.addState(State(dfa.size, Some(name))).toDFA()
    Definition.stateNames.append(name)
  }

  object Definition {

    val definition = Property[String]

    val stateNames = SeqProperty[String]
    val alphabetNames = SeqProperty[String]

    stateNames.listen(a => definition.set(dfa.toString))
    alphabetNames.listen(a => definition.set(dfa.toString))

  }

}
