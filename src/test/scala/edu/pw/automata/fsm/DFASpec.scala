package edu.pw.automata.fsm


import org.scalatest._

class DFASpec extends FlatSpec with Matchers{

  val q0 = State(0)
  val q1 = State(1)

  val s1 = 'a

  val f1 = DeltaFunction(q0, s1, q1)
  val f2 = DeltaFunction(q1, s1, q0)

  val dfa = new DFA(Set(q0, q1), Set(s1), Set(f1, f2), Some(q0), Set(q1))

  "DFA" should "go trough states" in {
    dfa.move(Seq(s1))     should be (Some(q1))
    dfa.move(Seq(s1, s1)) should be (Some(q0))
  }

}
