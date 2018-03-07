package models

import concurrent.{ ExecutionContext, Future }
import javax.inject.Inject
import org.apache.commons.math3.primes.Primes
import java.util.concurrent.Executors.newSingleThreadExecutor

object Prime {
  private val sec = ExecutionContext.fromExecutor(newSingleThreadExecutor)
  private var priorPrimes = PriorPrimes.empty
	
  def isPrime(n: Int): Boolean = Primes.isPrime(n)
		
  def getPriorPrimes(n: Int): Vector[Int] = priorPrimes.synchronized {
    priorPrimes.get(n) match {
      case Some(pps) => pps
      case None => 
        Future(priorPrimes.synchronized {
          priorPrimes = priorPrimes + n
        })(sec)
        Vector.empty[Int]
    }
  }
}