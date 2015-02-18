(ns euler.helper-test
  (:require [clojure.test :refer [deftest]]
            [clojure.test.check.generators :as gen]
            [euler.core-test :refer :all]
            [euler.helper :refer :all])
  (:use midje.sweet))

(deftest helper-tests
  (fact "prime-factors"
        (prime-factors 12) => '(2 2 3)
        (prime-factors 123) => '(3 41))
  
  (fact "amicable"
  	    (amicable? 220) => [220 284]
        (amicable? 284) => [284 220])

  (fact "parse-grid"
        (parse-grid "1 2 3 4" 2) => [[1 2] [3 4]]
        (parse-grid "1 2 3 4 5 6 7 8 9" 3) => [[1 2 3] [4 5 6] [7 8 9]]
        (fact-qc "return a vector of vectors"
                 [n gen/nat]
                 (parse-grid (str n) 1) => [[n]]))

  ; (fact-qc "factors vs divisors"
  ;         [n gen/nat]
  ;         (factors n) => (divisors n))
  )