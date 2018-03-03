package models

import concurrent.{ ExecutionContext, Future }
import javax.inject.Inject
import org.apache.commons.math3.primes.Primes
import java.util.concurrent.Executors.newSingleThreadExecutor

object Prime {
  private val sec = ExecutionContext.fromExecutor(newSingleThreadExecutor)
  private val lock = AnyRef
	
  def isPrime(n: Int): Boolean = Primes.isPrime(n)
		
  def getPriorPrimes(n: Int): Vector[Int] = lock.synchronized {
    if (PriorPrimes.contains(n)) PriorPrimes(n)
    else {
      Future {
        lock.synchronized(PriorPrimes += n)
      }(sec)
      Vector.empty[Int]
    }
  }
}