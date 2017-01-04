package edu.pw.automata.diagram

import edu.pw.automata.DFAService
import edu.pw.automata.fsm.State

object Controller {

  var input = ""
  var currentInputPosition = 1
  var current: Option[State] = None

  def setInput(word: String) = (input = word)

  def move(): Unit = {
    if(currentInputPosition <= input.length) {
      current = DFAService.dfa.move(input.substring(0, currentInputPosition))
      currentInputPosition += 1
    } else
      reset()
  }

  def reset() = {
    currentInputPosition = 0
    move()
  }

}
