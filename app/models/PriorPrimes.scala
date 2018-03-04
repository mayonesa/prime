package models

import org.apache.commons.math3.primes.Primes.isPrime
import PriorPrimes.{ Index, FirstPrime }

private[models] class PriorPrimes {
  /* keeps track of requested n's in order to determine which are first-timers or not */
  private var requestedNs = Set.empty[Int]
  
  /* mappings from all n's requested (and in between) to previous prime indices.
   * prevents need for linear search for future new requests */
  private var nToPrevPrimeIdxs = Vector.fill(3)(-1)

  /* numerically-ordered cache of earlier prior primes (prevents recalculation) */
  private var pps = Vector(FirstPrime)
	
  private[models] def get(n: Int) = 
    if (requestedNs(n)) Some(ppsTo(nToPrevPrimeIdxs(n))) 
    else None
	
  private[models] def += (n: Int) =
    // only update if n has actual prior primes
    if (n > FirstPrime) {
      val nNs = nToPrevPrimeIdxs.size
      // only update if the exhaustive n's are not already stored
      if (n >= nNs) {
        (nNs to n).foreach { aN =>
          nToPrevPrimeIdxs = nToPrevPrimeIdxs :+ (pps.size - 1)
          if (isPrime(aN)) pps = pps :+ aN
        }
      }
      requestedNs = requestedNs + n
    }
	
  private def ppsTo(i: Index) = pps.slice(0, i + 1)
}

private[models] object PriorPrimes {
  private type Index = Int
  private val FirstPrime = 2
  private[models] def apply() = new PriorPrimes
}