(ns euler.helper-test
  (:require [clojure.test :refer [deftest]]
            [midje.sweet :refer [fact]]
            [euler.helper :as helper]))

(deftest helper-tests
  (fact "prime-factors"
        (helper/prime-factors 12) => '(2 2 3)
        (helper/prime-factors 123) => '(3 41))

  (fact "amicable?"
        (helper/amicable? 220) => [220 284]
        (helper/amicable? 284) => [284 220])

  (fact "abundant?"
        (helper/abundant? 12) => true)

  (fact "abundant-sum?"
        (helper/abundant-sum? 12) => false
        (helper/abundant-sum? 24) => true)

  (fact "triangle"
        (last (take 1 helper/triangles)) => 1
        (last (take 10 helper/triangles)) => 55
        (last (take 100 helper/triangles)) => 5050)

  (fact "parse-grid"
        (helper/parse-grid "1 2 3 4" 2) => [[1 2] [3 4]]
        (helper/parse-grid "1 2 3 4 5 6 7 8 9" 3) => [[1 2 3] [4 5 6] [7 8 9]])

  (fact "collatz"
        (helper/collatz 12) => '(12 6 3 10 5 16 8 4 2 1))

  (fact "to-words"
        (helper/to-words 100) => "onehundred"
        (helper/to-words 115) => "onehundredandfifteen"
        (helper/to-words 342) => "threehundredandfortytwo")

  (fact "prime?"
        (helper/prime? 2) => true
        (helper/prime? 12) => false)

  (fact "narcissistic?"
        (helper/narcissistic? 1634 4)))
