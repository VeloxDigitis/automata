package edu.pw.automata.fsm

case class State(i: Int, name: Option[String] = None) {

  override def toString = name.getOrElse("q" + i)

}