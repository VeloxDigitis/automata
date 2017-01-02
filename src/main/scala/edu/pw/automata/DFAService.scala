package edu.pw.automata

import edu.pw.automata.diagram.GraphService
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
    val symbol = Symbol(s)
    dfa = dfa.addSymbol(symbol).toDFA()
    Definition.alphabetNames.append(symbol.toString())

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

  def addAccepting(state: String) = {
    val s = dfa.stateFromString(state).get

    if(dfa.isAccepting(s))
      dfa = dfa.removeAccepting(s).toDFA()
    else
      dfa = dfa.addAccepting(s).toDFA()

    Definition.reload
  }

  def setStarting(state: String): Boolean = {
    val s = dfa.stateFromString(state)

    if(s.isDefined)
      dfa = dfa.setInitial(s.get).toDFA()

    Definition.reload

    s.isDefined
  }

  def loadDemo() = (dfa = DFADemo.get())

  def reload() {
    Definition.stateNames.set(dfa.getStates.map(_.toString).toSeq)
    Definition.alphabetNames.set(dfa.getAlphabet.map(_.toString).toSeq)
    Definition.transitions.set(dfa.getStates.toSeq.map(state => {state.toString :: dfa.getAlphabet.toList.map(a => dfa.move(a, Some(state)).getOrElse("").toString)}))
  }

  object Definition {

    val definition = Property[String](dfa.toString)

    val stateNames = SeqProperty[String]
    val alphabetNames = SeqProperty[String]

    val transitions = SeqProperty[Seq[String]]

    stateNames.listen(_ => reload)
    alphabetNames.listen(_ => reload)

    def reload = {
      println(alphabetNames.get.map(_.toString) + " " + dfa.getAlphabet.map(_.toString))
      definition.set(dfa.toString)
      GraphService.repaint()
    }
  }

}
