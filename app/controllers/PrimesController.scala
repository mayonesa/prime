package controllers

import javax.inject.Inject
import play.api.mvc._
import models.Prime
import scala.concurrent.ExecutionContext
import play.api.libs.json.Json

class PrimesController @Inject() (cc: ControllerComponents,
																	prime: Prime)(implicit ec: ExecutionContext) extends AbstractController(cc) {
  def primes(n: Int) = Action.async { 
    val priorPrimesFut = prime.getPriorPrimes(n)
    for {
      isPrime <- prime.isPrime(n)
      priorPrimes <- priorPrimesFut
    } yield	Ok(Json.obj("isPrime" -> isPrime, "primes" -> Json.toJson(priorPrimes)))
  }
}