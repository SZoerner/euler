(ns euler.euler021-test
  (:require [clojure.test :refer [deftest]]
            [euler.euler021 :as e021]
            [midje.sweet :refer [fact]]))

(deftest euler021-tests
  (fact "Problem 21"
        (e021/p021) => 31626)

  (fact "Problem 22"
        (e021/p022) => 871198282)

  (fact "Problem 23"
        (e021/p023 [12]) => 12
        (e021/p023 (range 25)) => 276
        (e021/p023) => 4179871 :slow)

  (fact "Problem 24"
        (e021/p024) => '(2 7 8 3 9 1 5 4 6 0))

  (fact "Problem 25"
        (e021/p025) => 4782)

  (fact "Problem 26"
        (e021/rec-cycle 1 6) => '(6)
        (e021/rec-cycle 1 7) => '(1 4 2 8 5 7)
        (e021/p026) => 983)

  (fact "Problem 27"
        (e021/consec-primes 1 41) => 40                       ; n² + n + 41
        (e021/consec-primes -79 1601) => 80                    ; n² − 79n + 1601
        (e021/p027) => -59231 :slow)

  (fact "Problem 28"
        (e021/p028 3) => 25
        (e021/p028 5) => 101
        (e021/p028) => 669171001)

  (fact "Problem 29"
        (e021/p029 5) => 15
        (e021/p029) => 9183)

  (fact "Problem 30" :slow
        (e021/p030 4) => 19316
        (e021/p030) => 443839))
