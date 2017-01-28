package edu.pw.automata.diagram

import io.udash.properties.single.Property

import edu.pw.automata.DFAService
import edu.pw.automata.fsm.State

import scala.concurrent.ExecutionContext.Implicits.global

object Controller {

  var input = ""
  var formattedInput = ""
  var currentInputPosition = 0
  var current: Option[State] = None

  def setInput(word: String) = input = word

  val playing = Property[Boolean](false)
  
  def move(): Unit = {

	if(currentInputPosition == input.length)
		reset()
	else {
      formattedInput = s"Next symbol: ${if(currentInputPosition + 1 == input.length) "-" else input.charAt(currentInputPosition + 1)}"
      current = DFAService.dfa.move(input.substring(0, currentInputPosition + 1))
      currentInputPosition += 1
    }
  }

  def reset() = {
	playing.set(false)
    currentInputPosition = 0
    current = DFAService.dfa.getQ0
    formattedInput = s"Next symbol: ${input.charAt(0)}"
  }

}
