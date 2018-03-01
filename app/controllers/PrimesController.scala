package controllers

import javax.inject.Inject
import play.api.mvc._
import models.Prime
import concurrent.ExecutionContext
import play.api.libs.json.Json
import java.lang.Integer.valueOf 

class PrimesController @Inject() (cc: ControllerComponents, prime: Prime) extends AbstractController(cc) {
  def primes(nStr: String) = Action { 
    try {
      val n = valueOf(nStr)
      val priorPrimes = prime.getPriorPrimes(n)
      Ok(Json.obj("isPrime" -> prime.isPrime(n), "primes" -> priorPrimes))
    } catch {
      case _: NumberFormatException => 
        BadRequest(Json.obj("errors" -> Json.arr(s"input value, $nStr, must be an integer instead.")))
    }
  }
}