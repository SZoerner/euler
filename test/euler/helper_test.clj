(ns euler.helper-test
  (:require [clojure.test :refer [deftest]]
            [clojure.test.check.generators :as gen]
            [midje.sweet :refer [fact]]
            [euler.core-test :refer [fact-qc]]
            [euler.helper :refer :all]))

(deftest helper-tests
  (fact "prime-factors"
        (prime-factors 12) => '(2 2 3)
        (prime-factors 123) => '(3 41))

  (fact "amicable?"
        (amicable? 220) => [220 284]
        (amicable? 284) => [284 220])

  (fact "abundant?"
        (abundant? 12) => true)

  (fact "abundant-sum?"
        (abundant-sum? 12) => false
        (abundant-sum? 24) => true)

  (fact "triangle"
        (triangle 1) => 1
        (triangle 10) => 55
        (triangle 100) => 5050)

  (fact "parse-grid"
        (parse-grid "1 2 3 4" 2) => [[1 2] [3 4]]
        (parse-grid "1 2 3 4 5 6 7 8 9" 3) => [[1 2 3] [4 5 6] [7 8 9]]
        (fact-qc "return a vector of vectors"
                 [n gen/nat]
                 (parse-grid (str n) 1) => [[n]]))

  (fact "collatz"
        (collatz 12) => '(12 6 3 10 5 16 8 4 2 1))

  (fact "to-words"
        (to-words 100) => "onehundred"
        (to-words 115) => "onehundredandfifteen"
        (to-words 342) => "threehundredandfortytwo")

  (fact "prime?"
        (prime? 2) => true
        (prime? 12) => false)

  (fact "narcissistic?"
        (narcissistic? 1634 4))

   (fact-qc "factors vs count-divisors"
           [n gen/nat]
           (count (factors n)) => (count-divisors n))
  )
