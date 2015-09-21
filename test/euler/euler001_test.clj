(ns euler.euler001-test
  (:require [clojure.test :refer [deftest]]
            [euler.euler001 :as e001]
            [midje.sweet :refer [fact]]))

(deftest euler001-tests
  (fact "Problem 1"
        (e001/p001 5) => 3
        (e001/p001 10) => 23
        (e001/p001 1000) => 233168)

  (fact "Problem 2"
        (e001/p002 35) => 44
        (e001/p002 (* 4 1000 1000)) => 4613732)

  (fact "Problem 3"
        (e001/p003 13195) => 29
        (e001/p003 600851475143) => 6857)

  (fact "Problem 4"
        (e001/p004 100) => 9009
        (e001/p004 1000) => 906609)

  (fact "Problem 5"
        (e001/p005 10) => 2520
        (e001/p005 20) => 232792560)

  (fact "Problem 6"
        (e001/p006 10) => 2640
        (e001/p006 100) => 25164150)

  (fact "Problem 7"
        (e001/p007 6) => 13
        (e001/p007 10) => 29
        (e001/p007 10001) => 104743)

  (fact "Problem 8"
        (e001/p008 5 123456789) => 15120
        (e001/p008 5 (bigdec (slurp "resources/p008_digit.txt"))) => 40824)

  (fact "Problem 9"
        (e001/p009 (+ 3 4 5)) => (* 3 4 5)
        (e001/p009 1000) => (* 200 375 425))

  (fact "Problem 10"
        (e001/p010 10) => 17
        (e001/p010 2000000) => 142913828922))
