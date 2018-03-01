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
      p.getPriorPrimes(5) mustBe List(2, 3)
    } 
    "for 8 be empty at first" in {
      p.getPriorPrimes(8) mustBe empty
    }
    "for 8 be [2, 3, 5, 7] after the first time" in {
      p.getPriorPrimes(8) mustBe List(2, 3, 5, 7)
    } 
    "for 5 be [2, 3] after the first time and larger n's" in {
      p.getPriorPrimes(5) mustBe List(2, 3)
    } 
    "for negative be empty" in {
      p.getPriorPrimes(-10) mustBe empty
    } 
    "for negative be empty after the first time" in {
      p.getPriorPrimes(-10) mustBe empty
    } 
    "for 1 be empty" in {
      p.getPriorPrimes(1) mustBe empty
    } 
    "for 1 be empty after the first time" in {
      p.getPriorPrimes(1) mustBe empty
    } 
    "for 2 be empty at first" in {
      p.getPriorPrimes(2) mustBe empty
    }
    // "for 2 be empty after the first time" in {
    //   p.getPriorPrimes(2) mustBe empty
    // }
    "for 3 be empty at first" in {
      p.getPriorPrimes(3) mustBe empty
    }
    // "for 3 be [2] after the first time" in {
    //   p.getPriorPrimes(3) mustBe List(2)
    // }
  }
}