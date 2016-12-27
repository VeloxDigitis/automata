package edu.pw.automata

import edu.pw.automata.fsm.{DFA, DFADemo, State}
import io.udash.properties.seq.SeqProperty
import io.udash.properties.single.Property

import scala.concurrent.ExecutionContext.Implicits.global

object DFAService {

  var dfa = DFA.empty()

  def addState(name: Option[String]) = {
    val s = State(dfa.size, name)
    dfa = dfa.addState(s).toDFA()
    Definition.stateNames.append(s.toString)

    Definition.transitions.append(Seq.fill(Definition.alphabetNames.size + 1)(s.toString))
  }

  def addSymbol(name: Option[String]) = {
    val s = name.getOrElse(dfa.alphabetSize + "")
    dfa = dfa.addSymbol(Symbol(s)).toDFA()
    Definition.alphabetNames.append(s)

    val t = Definition.transitions
    t.set(t.get.map(e => e :+ e.head))
  }

  def setTransition(state: String, input: String, to: String) = {
    println(state + " " + input + " " + to)
  }

  def loadDemo() = dfa = DFADemo.get()

  def reload() {
    Definition.stateNames.set(dfa.getStates.map(_.toString).toSeq)
    Definition.alphabetNames.set(dfa.getAlphabet.map(_.toString).toSeq)

    //ADD TRANSTIONS

  }

  object Definition {

    val definition = Property[String](dfa.toString)

    val stateNames = SeqProperty[String]
    val alphabetNames = SeqProperty[String]

    val transitions = SeqProperty[Seq[String]]

    stateNames.listen(a => definition.set(dfa.toString))
    alphabetNames.listen(a => definition.set(dfa.toString))
    transitions.listen(a => {

    })

  }

}
