package edu.pw.automata.fsm;case class DeltaFunction(a: State, symbol: Symbol, b: State) {

  override def toString = s"$a: $symbol => $b"

}