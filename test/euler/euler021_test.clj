(ns euler.euler021-test
  (:require [clojure.test :refer :all]
            [euler.euler021 :refer :all]))

(deftest prob-021-test
  (testing "Problem 21"
    (is (= 31626 (prob-021)))))

(deftest prob-022-test
  (testing "Problem 22"
    (is (= 871198282 (prob-022)))))

(deftest prob-023-test
  (testing "Problem 23"
    (is (= 4179871 (prob-023)))))

(deftest prob-024-test
  (testing "Problem 24"
    (is (= '(2 7 8 3 9 1 5 4 6 0) (prob-024)))))

(deftest prob-025-test
  (testing "Problem 25"
    (is (= 4782 (prob-025)))))                                ; n² + n + 41

(deftest prob-027-test
  (testing "Problem 27"
    (is (= 40 (consec-primes 1 41)))                        ; n² + n + 41
    (is (= 80 (consec-primes -79 1601)))                    ; n² − 79n + 1601
    (is (= -59231 (prob-027)))))                              ; spoiler alert!
