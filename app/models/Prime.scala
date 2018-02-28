package models

import scala.concurrent.{ ExecutionContext, Future }
import javax.inject.Inject
import org.apache.commons.math3.primes.Primes
import collection.mutable

class Prime @Inject() (implicit ec: ExecutionContext) {
  private val priorPrimesMap = mutable.Map.empty[Int, Seq[Int]]
	
  def isPrime(n: Int): Boolean = Primes.isPrime(n)
		
  def getPriorPrimes(n: Int): Seq[Int] = priorPrimesMap.synchronized {
    if (priorPrimesMap.contains(n)) priorPrimesMap(n)
    else {
      Future(updatePriorPrimes(n))
      Seq.empty[Int]
    }
  }
	
  private def updatePriorPrimes(n: Int) = priorPrimesMap(n) = priorPrimes(n)
	
  private def priorPrimes(n: Int) = (2 until n).filter(isPrime)
}