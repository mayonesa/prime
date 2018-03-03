package models

import org.apache.commons.math3.primes.Primes.isPrime
import PriorPrimes.Index

private[models] class PriorPrimes {
  /* keeps track of requested n's in order to determine which are first-timers or not */
  private var requestedNs = Map.empty[Int, Index]
  
  /* mappings from all n's requested (and in between) to prior primes indices.
   * prevents need for linear search for future new requests */
  private var nToPpsIndices = Vector.empty[Index] 

  /* cache of earlier prior primes (prevents recalculation) */
  private var pps = Vector.empty[Int] 
	
  private[models] def get(n: Int) = requestedNs.get(n).map(ppsTo)
	
  private[models] def += (n: Int) =		
    if (n < 3) Vector.empty[Int]
    else if (n < nToPpsIndices.size) {        
      val j = nToPpsIndices(n) - 1
      requestedNs = requestedNs + (n -> j)
      ppsTo(j)
    } else {
      val start = if (pps.isEmpty) 0 else (pps.last + 1)
      (start until n).foreach { aN =>
        nToPpsIndices = nToPpsIndices :+ pps.size
        if (isPrime(aN)) pps = pps :+ aN
      }
      requestedNs = requestedNs + (n -> (pps.size - 1))
      pps
    }
	
  private def ppsTo(i: Index) = pps.slice(0, i + 1)
}

private[models] object PriorPrimes {
  private type Index = Int
  private[models] def apply() = new PriorPrimes
}