(ns euler.euler001-test
  (:require [clojure.test :refer [deftest is]]
            [euler.euler001 :as e001]))

(deftest problem-001
  (is (= (e001/p001 5) 3))
  (is (= (e001/p001 10) 23))
  (is (= (e001/p001 1000) 233168)))

(deftest problem-002
  (is (= (e001/p002 35) 44))
  (is (= (e001/p002 (* 4 1000 1000)) 4613732)))

(deftest problem-003
  (is (= (e001/p003 13195) 29))
  (is (= (e001/p003 600851475143) 6857)))

(deftest problem-004
  (is (= (e001/p004 100) 9009))
  (is (= (e001/p004 1000) 906609)))

(deftest problem-005
  (is (= (e001/p005 10) 2520))
  (is (= (e001/p005 20) 232792560)))

(deftest problem-006
  (is (= (e001/p006 10) 2640))
  (is (= (e001/p006 100) 25164150)))

(deftest problem-007
  (is (= (e001/p007 6) 13))
  (is (= (e001/p007 10) 29))
  (is (= (e001/p007 10001) 104743)))

(deftest problem-008
  (is (= (e001/p008 5 123456789) 15120))
  (is (= (e001/p008 5 (bigdec (slurp "resources/p008_digit.txt"))) 40824)))

(deftest problem-009
  (is (= (e001/p009 (+ 3 4 5)) (* 3 4 5)))
  (is (= (e001/p009 1000) (* 200 375 425))))

(deftest Problem-010
  (is (= (e001/p010 10) 17))
  (is (= (e001/p010 2000000) 142913828922)))
