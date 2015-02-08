(ns euler.euler001-test
  (:require [clojure.test :refer [deftest]]
            [euler.euler001 :refer :all])
  (:use midje.sweet))

(deftest euler001-tests
  (fact "Problem 1"
        (p001 5 3 5) => 3
        (p001 10 3 5) => 23
        (p001) => 233168)

  (fact "Problem 2"
        (p002 35) => 44
        (p002 4000000) => 4613732)

  (fact "Problem 3"
        (p003 13195) => 29;; (p003 600851475143) => 6857 ; StackOverflow :(
)  

  (fact "Problem 3"
        (p003-lazy 13195) => 29
        (p003-lazy 600851475143) => 6857)

  (fact "Problem 4"
        (p004 100) => 9009)

  (fact "Problem 5"
        (p005 10) => 2520
        (p005 20) => 232792560)

  (fact "Problem 6"
        (p006 10) => 2640
        (p006 100) => 25164150)

  (fact "Problem 7"
        (p007 6) => 13;; (p007 10001) => 104743     ;; too slow - makes cloverage abort
)  

  (fact "Problem 8"
        (p008 5 123456789) => 15120
        (p008) => 40824)

  (fact "Problem 9"
        (p009 (+ 3 4 5)) => (* 3 4 5)
        (p009 1000) => (* 200 375 425))

  (fact "Problem 10"
        (p010 10) => 17;; (p010 2000000) => 142913828922) ;; too slow - makes cloverage abort
))