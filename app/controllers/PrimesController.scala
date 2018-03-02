package controllers

import javax.inject.Inject
import play.api.mvc._
import models.Prime._
import play.api.libs.json.Json
import java.lang.Integer.valueOf 
import concurrent.{ ExecutionContext, Future }
	
class PrimesController @Inject() (cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {
  def primes(nStr: String) = Action.async { 
    try {
      val n = valueOf(nStr)
      val priorPrimesFut = Future(getPriorPrimes(n))
      for {
        p <- Future(isPrime(n))
        pps <- priorPrimesFut
      } yield	Ok(Json.obj("isPrime" -> p, "primes" -> pps))
    } catch {
      case _: NumberFormatException => 
        Future(BadRequest(Json.obj("errors" -> Json.arr(s"input value, $nStr, must be an integer instead."))))
    }
  }
}