package edu.pw.automata.fsm

import Types._

class DFA(Q:   States,
          Σ:   Alphabet,
          δ:   Delta,
          q0:  Option[State],
          F:   States)
  extends FSM(Q, Σ, δ, q0, F) {

  override def addDelta(d: DeltaFunction): FSM   = (Q, Σ, δ.filter(a => !(a.a == d.a && a.symbol == d.symbol)) + d, q0, F)

  def move(word: Word): Option[State] = move(word, q0)

  def move(symbol: Symbol, s: Option[State]): Option[State] = {
    val d = δ.filter(p => p.symbol == symbol && p.a == s.getOrElse(None))

    if(d.isEmpty)
      None
    else
      Some(d.head.b)
  }

  def move(word: Word, s: Option[State]): Option[State] = word.toList match{
    case a :: Nil  => move(a, s)
    case a :: tail => move(tail, move(a, s))
    case _ => None
  }

  def move(word: String, s: Option[State]): Option[State] = {
    move(for (x <- word.toList) yield Symbol(x toString), s)
  }

  def move(word: String): Option[State] = {
    move(word, q0)
  }

  def hasDelta(symbol: Symbol, s: State) = δ.exists(p => p.a == s && p.symbol == symbol)

}

object DFA {

  def empty() = new DFA(Set.empty, Set.empty, Set.empty, None, Set.empty)

}