package edu.pw.automata.fsm

object Types {

  type States   = Set[State]
  type Alphabet = Set[Symbol]
  type Word     = Seq[Symbol]
  type Delta    = Set[DeltaFunction]

}
