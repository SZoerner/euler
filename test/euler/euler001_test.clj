(ns euler.euler001-test
  (:require [clojure.test :refer [deftest]]
            [euler.euler001 :as e001]
            [midje.sweet :refer [fact]]))

(deftest euler001-tests
  (fact "Problem 1"
        (e001/p001 5 3 5) => 3
        (e001/p001 10 3 5) => 23
        (e001/p001) => 233168)

  (fact "Problem 2"
        (e001/p002 35) => 44
        (e001/p002) => 4613732)

  (fact "Problem 3"
        (e001/p003 13195) => 29
        (e001/p003) => 6857)

  (fact "Problem 4"
        (e001/p004 100) => 9009
        (e001/p004) => 906609)

  (fact "Problem 5"
        (e001/p005 10) => 2520
        (e001/p005) => 232792560)

  (fact "Problem 6"
        (e001/p006 10) => 2640
        (e001/p006) => 25164150)

  (fact "Problem 7"
        (e001/p007 6) => 13
        (e001/p007 10) => 29
        (e001/p007) => 104743)

  (fact "Problem 8"
        (e001/p008 5 123456789) => 15120
        (e001/p008) => 40824)

  (fact "Problem 9"
        (e001/p009 (+ 3 4 5)) => (* 3 4 5)
        (e001/p009) => (* 200 375 425))

  (fact "Problem 10"
        (e001/p010 10) => 17
        (e001/p010) => 142913828922))
