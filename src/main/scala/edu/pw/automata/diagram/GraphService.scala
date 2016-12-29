package edu.pw.automata.diagram

import edu.pw.automata.fsm.State
import edu.pw.automata.{DFAService, DOMGlobalScope}
import io.udash.wrappers.jquery._

object GraphService {

  var current: Option[State] = None

  def move() = {
    val a = if(current.isEmpty) {DFAService.dfa.getStates.head} else {current.get}
    current = DFAService.dfa.move("0", Some(a))
  }


  def repaint() = {

    jQ("#diagram-main").empty()

    val dfa = DFAService.dfa

    val accepting = dfa.getStates.filter(dfa.isAccepting(_))


    val s = new StringBuilder("digraph finite_state_machine {rankdir=LR;" +
      "node [shape = doublecircle];" + accepting.mkString(" ") + {if(accepting.isEmpty) ("") else (";")} +
      "node [shape = circle];")

    dfa.getDelta.foreach(e => s.append(e.a.toString + " -> " + e.b.toString + " [ label = \"" + e.symbol.toString() + "\" ];"))

    if(current.isDefined)
      s.append(current.get.toString + " [fillcolor = red, style=filled];")

    s.append("}")

    jQ("#diagram-main").append(DOMGlobalScope.drawGraph(s.toString()))
  }



}
