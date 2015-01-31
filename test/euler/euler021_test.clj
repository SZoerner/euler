(ns euler.euler021-test
  (:require [clojure.test :refer [deftest]]
            [euler.euler021 :refer :all])
  (:use midje.sweet))

(deftest tests
(fact "Problem 21"
    (p021) => 31626)

(fact "Problem 22"
    (p022) => 871198282)

(fact "Problem 23"
    (p023) => 4179871)

(fact "Problem 24"
    (p024) => '(2 7 8 3 9 1 5 4 6 0))

(fact "Problem 25"
    (p025) => 4782)                            ; n² + n + 41

(fact "Problem 27"
    (consec-primes 1 41) => 40                       ; n² + n + 41
    (consec-primes -79 1601) => 80                    ; n² − 79n + 1601
    (p027) => -59231)                              ; spoiler alert!

(fact "Problem 28"
    (p028 3) => 25
    (p028 5) => 101
    (p028 1001) => 669171001)

(fact "Problem 29"
    (p029 5) => 15
    (p029 100) => 9183)

(fact "Problem 30"
    (p030 4) => 19316
    (p030 5) => 443839)
)