package models

import org.apache.commons.math3.primes.Primes.isPrime
import annotation.tailrec
import PriorPrimes._

private[models] class PriorPrimes {
  private var ppiMap = Map.empty[Int, Index]
  // TODO: consider Vector
  private var pps = Seq.empty[Int]
	
  private[models] def apply(n: Int) = ppsTo(ppiMap(n))
	
  private[models] def contains(n: Int) = ppiMap.contains(n)
	
  private[models] def += (n: Int) =		
    if (n < 3) Seq.empty[Int] 
    else {
      val i = pps.indexWhere(_ >= n) - 1
      if (found(i)) {
        ppiMap = ppiMap + (n -> i)
        ppsTo(i)
      } else { 
        val start = if (pps.isEmpty) 2 else (pps.last + 1)
        // TODO: consider withFilter
        (start until n).filter(isPrime).foreach { prime =>
          pps = pps :+ prime
        }
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