package models

import concurrent.{ ExecutionContext, Future }
import javax.inject.Inject
import org.apache.commons.math3.primes.Primes
import java.util.concurrent.Executors.newSingleThreadExecutor

object Prime {
  private val priorPrimes = new PriorPrimes
  private val sec = ExecutionContext.fromExecutor(newSingleThreadExecutor)
	
  def isPrime(n: Int): Boolean = Primes.isPrime(n)
		
  def getPriorPrimes(n: Int): Seq[Int] = priorPrimes.synchronized {
    if (priorPrimes.contains(n)) priorPrimes(n)
    else {
      Future {
        priorPrimes.synchronized(priorPrimes += n)
      }(sec)
      Vector.empty[Int]
    }
  }
}