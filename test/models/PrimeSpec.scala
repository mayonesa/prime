package models

import org.scalatestplus.play._
import concurrent.ExecutionContext.Implicits.global

class PrimeSpec extends PlaySpec {
  private val p = new Prime()
	
  "Prior primes" must {
    "for 5 be empty at first" in {
      p.getPriorPrimes(5) mustBe empty
    }
    "for 5 be [2, 3] after the first time" in {
      p.getPriorPrimes(5) mustBe IndexedSeq(2, 3)
    } 
    "for 8 be empty at first" in {
      p.getPriorPrimes(8) mustBe empty
    }
    "for 8 be [2, 3, 5, 7] after the first time" in {
      p.getPriorPrimes(8) mustBe IndexedSeq(2, 3, 5, 7)
    } 
  }
}