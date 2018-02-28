package controllers

import javax.inject.Inject
import play.api.mvc._
import models.Prime
import concurrent.ExecutionContext
import play.api.libs.json.Json

class PrimesController @Inject() (cc: ControllerComponents, prime: Prime) extends AbstractController(cc) {
  def primes(n: Int) = Action { 
		val priorPrimes = prime.getPriorPrimes(n)
    Ok(Json.obj("isPrime" -> prime.isPrime(n), "primes" -> priorPrimes))
  }
}