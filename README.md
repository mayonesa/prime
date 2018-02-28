# prime
REST API that accepts an integer value and upon the first request, returns whether the number is prime or not. When this request is received, another task is kicked off which calculates and stores all prime numbers less than the number received. After these numbers are calculated, subsequent requests with the original number return both whether the number is prime or not and a list of all primes less than that number.

Example:

GET: /primes/5
-> { isPrime: true, primes:[]}

GET: /prime/5
-> { isPrime: true, primes: [2,3] }

GET: /prime/8
-> { isPrime: false, primes: [] }

GET: /prime/8
-> { isPrime: false, primes: [2,3,5,7] }

To Run:

prime> sbt run

notes:
 - errors not Jsonified (e.g., {"errors": ["'abc' is not an integer"]}) for sake of code readibilty/simplicity.
 - a running jar can be provided if installing sbt is not desirable.