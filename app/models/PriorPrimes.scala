package models

import org.apache.commons.math3.primes.Primes.isPrime
import annotation.tailrec
import PriorPrimes._

private[models] object PriorPrimes {
  private type Index = Int

  private var requestedNs = Map.empty[Int, Index]
  private var nToPpiMap = Map.empty[Int, Index]
  private var pps = Vector.empty[Int]
	
  private[models] def apply(n: Int) = ppsTo(requestedNs(n))
	
  private[models] def contains(n: Int) = requestedNs.contains(n)
	
  private[models] def += (n: Int) =		
    if (n < 3) Vector.empty[Int] 
    else nToPpiMap.get(n).fold {
      val start = if (pps.isEmpty) 2 else (pps.last + 1)
      (start until n).foreach { aN =>
        nToPpiMap = nToPpiMap + (aN -> pps.size)
        if (isPrime(aN)) pps = pps :+ aN
      }
      requestedNs = requestedNs + (n -> (pps.size - 1))
      pps
    } { i =>
      val j = i - 1
      requestedNs = requestedNs + (n -> j)
      ppsTo(j)
    } 
	
  private def ppsTo(i: Index) = pps.slice(0, i + 1)
}