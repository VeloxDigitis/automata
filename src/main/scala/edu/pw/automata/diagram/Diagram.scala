package edu.pw.automata.diagram

import edu.pw.automata.fsm.Types.Delta

case class Diagram(options: Map[String, String], nodes: Delta) {

 // def addOption(name: String, value: String) = new Diagram(options + (name -> value))

  override def toString: String = {
    val sb = new StringBuilder

    sb.append("digraph finite_state_machine {\n")

    "XD"
  }

}
