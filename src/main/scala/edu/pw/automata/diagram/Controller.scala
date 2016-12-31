package edu.pw.automata.diagram

import edu.pw.automata.DFAService
import edu.pw.automata.fsm.State

object Controller {

  var input = ""
  var currentInputPosition = 0
  var current: Option[State] = None

  def setInput(word: String) = (input = word)

  def move() = {
    println(current.getOrElse("").toString)

    if(currentInputPosition < input.length) {
      current = DFAService.dfa.move(input.substring(0, currentInputPosition))
      currentInputPosition += 1
    } else
      stop()
  }

  def stop() = {
    currentInputPosition = 0
  }

}
