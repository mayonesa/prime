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
    "for 2 be empty after the first time" in {
      p.getPriorPrimes(2) mustBe empty
    } 
    "for 3 be empty at first" in {
      p.getPriorPrimes(3) mustBe empty
    }
    "for 3 be [2] after the first time" in {
      p.getPriorPrimes(3) mustBe List(2)
    } 
    "for 4 be empty at first" in {
      p.getPriorPrimes(4) mustBe empty
    }
    "for 4 be [2, 3] after the first time" in {
      p.getPriorPrimes(4) mustBe List(2, 3)
    } 
    "for 6 be empty at first" in {
      p.getPriorPrimes(6) mustBe empty
    }
    "for 6 be [2, 3, 5] after the first time" in {
      p.getPriorPrimes(6) mustBe List(2, 3, 5)
    } 
    "for 7 be empty at first" in {
      p.getPriorPrimes(7) mustBe empty
    }
    "for 7 be [2, 3, 5] after the first time" in {
      p.getPriorPrimes(7) mustBe List(2, 3, 5)
    } 
    "for 12 be empty at first" in {
      p.getPriorPrimes(12) mustBe empty
    }
    "for 12 be [2, 3, 5, 7, 11] after the first time" in {
      p.getPriorPrimes(12) mustBe List(2, 3, 5, 7, 11)
    } 
    "for 9 be empty at first" in {
      p.getPriorPrimes(9) mustBe empty
    }
    "for 9 be [2, 3, 5, 7] after the first time" in {
      p.getPriorPrimes(9) mustBe List(2, 3, 5, 7)
    } 
    "for 10 be empty at first" in {
      p.getPriorPrimes(10) mustBe empty
    }
    "for 10 be [2, 3, 5, 7] after the first time" in {
      p.getPriorPrimes(10) mustBe List(2, 3, 5, 7)
    } 
    "for 11 be empty at first" in {
      p.getPriorPrimes(11) mustBe empty
    }
    "for 11 be [2, 3, 5, 7] after the first time" in {
      p.getPriorPrimes(11) mustBe List(2, 3, 5, 7)
    } 
    "for 20 be empty at first" in {
      p.getPriorPrimes(20) mustBe empty
    }
    "for 20 be [2, 3, 5, 7, 11, 13, 17, 19] after the first time" in {
      p.getPriorPrimes(20) mustBe List(2, 3, 5, 7, 11, 13, 17, 19)
    }
    "for 19 be empty at first" in {
      p.getPriorPrimes(19) mustBe empty
    }
    "for 19 be [2, 3, 5, 7, 11, 13, 17] after the first time" in {
      p.getPriorPrimes(19) mustBe List(2, 3, 5, 7, 11, 13, 17)
    } 
   "for computationally-intensive be empty at first" in {
      p.getPriorPrimes(1000) mustBe empty
    }
    "for computationally-intensive must not be empty after the first time" in {
      p.getPriorPrimes(1000) must not be empty
    } 
  }
}