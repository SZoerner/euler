(ns euler.euler021-test
  (:require [clojure.test :refer :all]
            [euler.euler021 :refer :all]))

(deftest p021-test
  (testing "Problem 21"
    (is (= 31626 (p021)))))

(deftest p022-test
  (testing "Problem 22"
    (is (= 871198282 (p022)))))

(deftest p023-test
  (testing "Problem 23"
    (is (= 4179871 (p023)))))

(deftest p024-test
  (testing "Problem 24"
    (is (= '(2 7 8 3 9 1 5 4 6 0) (p024)))))

(deftest p025-test
  (testing "Problem 25"
    (is (= 4782 (p025)))))                                ; n² + n + 41

(deftest p027-test
  (testing "Problem 27"
    (is (= 40 (consec-primes 1 41)))                        ; n² + n + 41
    (is (= 80 (consec-primes -79 1601)))                    ; n² − 79n + 1601
    (is (= -59231 (p027)))))                              ; spoiler alert!

(deftest p028-test
  (testing "Problem 28"
    (is (= 25 (p028 3)))
    (is (= 101 (p028 5)))
    (is (= 669171001 (p028 1001)))))

(deftest p029-test
  (testing "Problem 29"
    (is (= 15 (p029 5)))
    (is (= 9183 (p029 100)))))

(deftest p030-test
  (testing "Problem 30"
    (is (= 19316 (p030 4)))
    (is (= 443839 (p030 5)))))