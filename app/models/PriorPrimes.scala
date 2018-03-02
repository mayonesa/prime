package models

import org.apache.commons.math3.primes.Primes.isPrime
import annotation.tailrec
import PriorPrimes._

private[models] class PriorPrimes {
  private var ppiMap = Map.empty[Int, Index]
  private var pps = Vector.empty[Int]
	
  private[models] def apply(n: Int) = ppsTo(ppiMap(n))
	
  private[models] def contains(n: Int) = ppiMap.contains(n)
	
  private[models] def += (n: Int) =		
    if (n < 3) Vector.empty[Int] 
    else {
      val i = pps.indexWhere(_ >= n) - 1
      if (found(i)) {
        ppiMap = ppiMap + (n -> i)
        ppsTo(i)
      } else { 
        val start = if (pps.isEmpty) 2 else (pps.last + 1)
        for {
          nCurr <- start until n
          if isPrime(nCurr)
        } pps = pps :+ nCurr
        ppiMap = ppiMap + (n -> (pps.size - 1))
        pps
      }
    }
	
  private def ppsTo(i: Index) = pps.slice(0, i + 1)
}

private object PriorPrimes {
  private type Index = Int
  private def found(i: Index) = i != -2
} 