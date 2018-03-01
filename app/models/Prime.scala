package models

import concurrent.{ ExecutionContext, Future }
import javax.inject.Inject
import org.apache.commons.math3.primes.Primes

class Prime @Inject() (implicit ec: ExecutionContext) {
  private val priorPrimes = PriorPrimes()
	
  def isPrime(n: Int): Boolean = Primes.isPrime(n)
		
  def getPriorPrimes(n: Int): Seq[Int] = priorPrimes.synchronized {
    if (priorPrimes.contains(n)) priorPrimes(n)
    else {
      Future(priorPrimes += n)
      Seq.empty[Int]
    }
  }
}