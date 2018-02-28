package models

import scala.concurrent.{ ExecutionContext, Future }
import javax.inject.Inject
import org.apache.commons.math3.primes.Primes
import common.PrimesMap

class Prime @Inject() (implicit ec: ExecutionContext) {
  private val priorPrimesMap = PrimesMap.empty
	
  def isPrime(n: Int): Future[Boolean] = Future(Primes.isPrime(n))
		
  def getPriorPrimes(n: Int): Future[Seq[Int]] = Future(priorPrimesMap.synchronized {
    if (priorPrimesMap.contains(n)) priorPrimesMap(n)
    else {
      Future(updatePriorPrimes(n))
      Seq.empty[Int]
    }
  })
	
  private def updatePriorPrimes(n: Int) = priorPrimesMap(n) = priorPrimes(n)
	
  private def priorPrimes(n: Int) = (2 until n).filter(Primes.isPrime(_))
}