package edu.pw.automata.fsm

import org.scalatest._

class FSMSpec extends FlatSpec with Matchers{

  val q0 = State(0)
  val q1 = State(1)

  val s1 = 'a

  val f1 = DeltaFunction(q0, s1, q1)
  val f2 = DeltaFunction(q1, s1, q0)

  val dfa = new FSM(Set(q0, q1), Set(s1), Set(f1, f2), Some(q0), Set(q1))

  "FSM" should "identify DFA" in {
    dfa.isDFA should be(true)
  }

  val q2 = State(2)
  val f3 = DeltaFunction(q0, s1, q2)

  val nfa = new FSM(Set(q0, q1, q2), Set(s1), Set(f1, f2, f3), Some(q0), Set(q1, q2))

  it should "identify NFA" in {
    nfa.isNFA should be(true)
  }

}