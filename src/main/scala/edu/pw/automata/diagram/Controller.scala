package edu.pw.automata.diagram

import edu.pw.automata.DFAService
import edu.pw.automata.fsm.State

object Controller {

  var input = ""
  var formattedInput = ""
  var currentInputPosition = 0
  var current: Option[State] = None

  def setInput(word: String) = input = word

  def move(): Unit = {

    if(currentInputPosition < input.length) {
      formattedInput = s"Next symbol: ${if(currentInputPosition + 1 == input.length) "Reset" else input.charAt(currentInputPosition + 1)}"
      current = DFAService.dfa.move(input.substring(0, currentInputPosition + 1))
      currentInputPosition += 1
    } else
      reset()
  }

  def reset() = {
    currentInputPosition = 0
    current = DFAService.dfa.getQ0
    formattedInput = s"Next symbol: ${input.charAt(0)}"
    //move()
  }

}
