package edu.pw.automata.fsm

import edu.pw.automata.DFAService
import io.udash.properties.{Invalid, Valid, ValidationResult, Validator}

import scala.concurrent.{ExecutionContext, Future}

class ExistingStatesValidator extends Validator[String]{

  def apply(name: String)(implicit ec: ExecutionContext): Future[ValidationResult] = Future {
    if (name.isEmpty || DFAService.dfa.getStates.map(_.toString).contains(name)) Valid
    else Invalid("There is no such state!")
  }
}
