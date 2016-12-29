package edu.pw.automata

import edu.pw.automata.fsm.{DFA, DFADemo, DeltaFunction, State}
import io.udash.properties.seq.SeqProperty
import io.udash.properties.single.Property

import scala.concurrent.ExecutionContext.Implicits.global

object DFAService {

  var dfa = DFA.empty()

  def addState(name: Option[String]) = {
    val s = State(dfa.size, name)
    dfa = dfa.addState(s).toDFA()
    Definition.stateNames.append(s.toString)

    if(dfa.size == 1)
      dfa = dfa.setInitial(s).toDFA()

    Definition.transitions.append(Seq.fill(Definition.alphabetNames.size + 1)(s.toString))
  }

  def addSymbol(name: Option[String]) = {
    val s = name.getOrElse(dfa.alphabetSize + "")
    dfa = dfa.addSymbol(Symbol(s)).toDFA()
    Definition.alphabetNames.append(s)

    val t = Definition.transitions
    t.set(t.get.map(e => e :+ e.head))
  }

  def setTransition(state: String, input: String, state2: String) = {
    val from  = dfa.stateFromString(state)
    val symbol = dfa.symbolFromString(input)
    val to    = dfa.stateFromString(state2)

    dfa = dfa.addDelta(new DeltaFunction(from.get, symbol.get, to.get)).toDFA()

    Definition.reload
  }

  def addAccepting(state: String): Unit = {
    val s = dfa.stateFromString(state).get

    if(dfa.isAccepting(s))
      dfa = dfa.removeAccepting(s).toDFA()
    else
      dfa = dfa.addAccepting(s).toDFA()

    Definition.reload
  }

  def loadDemo() = dfa = DFADemo.get()

  def reload() {
    Definition.stateNames.set(dfa.getStates.map(_.toString).toSeq)
    Definition.alphabetNames.set(dfa.getAlphabet.map(_.toString).toSeq)
  }

  object Definition {

    val definition = Property[String](dfa.toString)

    val stateNames = SeqProperty[String]
    val alphabetNames = SeqProperty[String]

    val transitions = SeqProperty[Seq[String]]

    stateNames.listen(_ => reload)
    alphabetNames.listen(_ => reload)

    def reload = definition.set(dfa.toString)
  }

}
