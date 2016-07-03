(ns euler.euler021-test
  (:require [clojure.test :refer [deftest is]]
            [euler.euler021 :as e021]))

(deftest problem-021
  (is (= 31626 (e021/p021))))

(deftest problem-022
  (is (= 871198282 (e021/p022))))

(deftest problem-023
  (is (= 12 (e021/p023 [12])))
  (is (= 276 (e021/p023 (range 25))))
  (is (= 4179871 (e021/p023))))

(deftest problem-024
  (is (= [2 7 8 3 9 1 5 4 6 0] (e021/p024))))

(deftest problem-025
  (is (= 4782 (e021/p025))))

(deftest problem-026
  (is (= [6] (e021/rec-cycle 1 6)))
  (is (= [1 4 2 8 5 7] (e021/rec-cycle 1 7)))
  (is (= 983 (e021/p026))))

(deftest problem-027
  (is (= 40 (e021/consec-primes 1 41)))           ; n² + n + 41
  (is (= 80 (e021/consec-primes -79 1601)))       ; n² − 79n + 1601
  (is (= -59231 (e021/p027))))

(deftest problem-028
  (is (= 25 (e021/p028 3)))
  (is (= 101 (e021/p028 5)))
  (is (= 669171001 (e021/p028))))

(deftest problem-029
  (is (= 15 (e021/p029 5)))
  (is (= 9183 (e021/p029))))

(deftest problem-030
  (is (= 19316 (e021/p030 4)))
  (is (= 443839 (e021/p030))))
