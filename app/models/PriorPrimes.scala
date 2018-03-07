package models

private[models] trait PriorPrimes {
  private[models] def get(n: Int): Option[Vector[Int]]
  private[models] def + (n: Int): PriorPrimes
}

import org.apache.commons.math3.primes.Primes.isPrime
import PriorPrimes.PriorPrimesImpl

private[models] object PriorPrimes {
  private type Index = Int

  private val FirstPrime = 2
  private val NoPrevPrimeIdx = -1

  private[models] def empty: PriorPrimes =
    new PriorPrimesImpl(Set.empty[Int], Vector.fill(3)(NoPrevPrimeIdx), Vector(FirstPrime))

  /** @param requestedNs: keeps track of requested n's in order to determine which are first-timers or not
   *  @param nToPrevPrimeIdxs: mappings from all n's requested (and in between) to previous prime indices.
   *.                          the indices are the n's and the values are the `pps` indices.
   *                           prevents need for linear search for future new requests
   *  @param pps: numerically-ordered cache of earlier prior primes (prevents recalculation)
   */
  private class PriorPrimesImpl(requestedNs: Set[Int], nToPrevPrimeIdxs: Vector[Index], pps: Vector[Int]) extends PriorPrimes {
    private[models] def get(n: Int) =
      if (requestedNs(n)) Some(ppsTo(nToPrevPrimeIdxs(n)))
      else None

    private[models] def + (n: Int) =
      if (n > FirstPrime) {
        val (newNToPrevPrimeIds, newPps) = (nToPrevPrimeIdxs.size to n).foldLeft((nToPrevPrimeIdxs, pps)) { 
          case ((aNToPrevPrimeIdxs, aPps), aN) =>
            (aNToPrevPrimeIdxs :+ (aPps.size - 1), if (isPrime(aN)) aPps :+ aN else aPps)
        }
        new PriorPrimesImpl(requestedNs + n, newNToPrevPrimeIds, newPps)
      } else this

    private def ppsTo(i: Index) = pps.slice(0, i + 1)
  }
}