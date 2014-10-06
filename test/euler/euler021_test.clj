(ns euler.euler021-test
  (:require [clojure.test :refer :all]
            [euler.euler021 :refer :all]))

(deftest prob-027-test
  (testing "Problem 27"
    (is (= 40 (consec-primes (prime-gen 1 41))))            ; n² + n + 41
    (is (= 80 (consec-primes (prime-gen -79 1601))))        ; n² − 79n + 1601
    (is (= -59231 prob-027))))                              ; spoiler alert!
