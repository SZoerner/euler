(ns euler.euler021-test
  (:require [clojure.test :refer [deftest]]
            [midje.sweet :refer [fact]]
            [euler.euler021 :refer :all]))

(deftest euler021-tests
  (fact "Problem 21"
        (p021) => 31626)

  (fact "Problem 22"
        (p022) => 871198282)

  (fact "Problem 23"
        (p023 [12]) => 12
        (p023 (range 25)) => 276
        (p023) => 4179871)

  (fact "Problem 24"
        (p024) => '(2 7 8 3 9 1 5 4 6 0))

  (fact "Problem 25"
        (p025) => 4782)

  (fact "Problem 26"
        (rec-cycle 1 6) => '(6)
        (rec-cycle 1 7) => '(1 4 2 8 5 7)
        (p026) => 983)

  (fact "Problem 27"
        (consec-primes 1 41) => 40                       ; n² + n + 41
        (consec-primes -79 1601) => 80                    ; n² − 79n + 1601
        ;(p027) => -59231 ;; too slow - makes cloverage abort
  )

  (fact "Problem 28"
        (p028 3) => 25
        (p028 5) => 101
        (p028) => 669171001)

  (fact "Problem 29"
        (p029 5) => 15
        (p029) => 9183)

  ;; (fact "Problem 30" :slow ;; too slow - makes cloverage abort
  ;;      (p030 4) => 19316
  ;;      (p030 5) => 443839)
)
