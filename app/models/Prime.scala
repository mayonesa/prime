package models

import concurrent.{ ExecutionContext, Future }
import javax.inject.Inject
import org.apache.commons.math3.primes.Primes
import java.util.concurrent.Executors.newSingleThreadExecutor

object Prime {
  private val lock = AnyRef
  private val priorPrimes = PriorPrimes(lock)
  implicit private val ec = ExecutionContext.fromExecutor(newSingleThreadExecutor)
	
  def isPrime(n: Int): Boolean = Primes.isPrime(n)
		
  def getPriorPrimes(n: Int): Seq[Int] = lock.synchronized {
    if (priorPrimes.contains(n)) priorPrimes(n)
    else {
      Future(priorPrimes += n)
      Seq.empty[Int]
    }
  }
}