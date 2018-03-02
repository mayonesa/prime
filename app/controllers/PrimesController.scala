package controllers

import javax.inject.Inject
import play.api.mvc._
import models.Prime._
import play.api.libs.json.Json
import java.lang.Integer.valueOf 
	
class PrimesController @Inject() (cc: ControllerComponents) extends AbstractController(cc) {
  def primes(nStr: String) = Action { 
    try {
      val n = valueOf(nStr)
      val priorPrimes = getPriorPrimes(n)
      Ok(Json.obj("isPrime" -> isPrime(n), "primes" -> priorPrimes))
    } catch {
      case _: NumberFormatException => 
        BadRequest(Json.obj("errors" -> Json.arr(s"input value, $nStr, must be an integer instead.")))
    }
  }
}