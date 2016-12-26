package edu.pw.automata.fsm

object DFADemo {

  val q0 = State(0)
  val q1 = State(1)

  val s1 = 'a

  val f1 = DeltaFunction(q0, s1, q1)
  val f2 = DeltaFunction(q1, s1, q0)

  val dfa = new DFA(Set(q0, q1), Set(s1), Set(f1, f2), Some(q0), Set(q1))

  def get(): DFA = dfa

}
