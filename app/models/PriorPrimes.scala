package models

import org.apache.commons.math3.primes.Primes
import annotation.tailrec
import collection.SortedMap

private[models] class PriorPrimes {
  private var ppiMap = SortedMap.empty[Int, Int]
  private var pps = Seq.empty[Int]
	
  private[models] def apply(n: Int) = ppsTo(ppiMap(n))
	
  private[models] def contains(n: Int) = ppiMap.contains(n)
	
  private[models] def += (n: Int) = synchronized {

    @tailrec
    def loop(aPpiMap: SortedMap[Int, Int], prevI: Option[Int]): Option[Int] =
      if (aPpiMap.isEmpty) None
      else {
        val kv = aPpiMap.head
        val currN = kv._1
        if (currN > n) if (prevI.isEmpty) Some(0) else prevI
        else loop(aPpiMap.tail, Some(kv._2))
      }
		
    if (n < 3) Seq.empty[Int] 
    else loop(ppiMap, None) match {
      case Some(i) => 
        ppiMap = ppiMap + (n -> i)
        ppsTo(i)
      case _ => 
        val start = if (pps.isEmpty) 2 else (pps.last + 1)
        (start until n).filter(Primes.isPrime(_)).foreach { prime =>
          pps = pps :+ prime
        }
        ppiMap = ppiMap + (n -> (pps.size - 1))
        pps
    }
  }
	
  private def ppsTo(i: Int) = pps.slice(0, i + 1)
}

private[models] object PriorPrimes {
  private[models] def apply(): PriorPrimes = new PriorPrimes
}