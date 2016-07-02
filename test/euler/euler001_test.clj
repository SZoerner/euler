(ns euler.euler001-test
  (:require [clojure.test :refer [deftest is]]
            [euler.euler001 :as e001]))

(deftest problem-001
  (is (= 3 (e001/p001 5)))
  (is (= 23 (e001/p001 10)))
  (is (= 233168 (e001/p001 1000))))

(deftest problem-002
  (is (= 44 (e001/p002 35)))
  (is (= 4613732 (e001/p002 (* 4 1000 1000)))))

(deftest problem-003
  (is (= 29 (e001/p003 13195)))
  (is (= 6857 (e001/p003 600851475143))))

(deftest problem-004
  (is (= 9009 (e001/p004 100)))
  (is (= 906609 (e001/p004 1000))))

(deftest problem-005
  (is (= 2520 (e001/p005 10)))
  (is (= 232792560 (e001/p005 20))))

(deftest problem-006
  (is (= 2640 (e001/p006 10)))
  (is (= 25164150 (e001/p006 100))))

(deftest problem-007
  (is (= 13 (e001/p007 6)))
  (is (= 29 (e001/p007 10)))
  (is (= 104743 (e001/p007 10001))))

(deftest problem-008
  (is (= 15120 (e001/p008 5 123456789)))
  (is (= 40824 (e001/p008 5 (bigdec (slurp "resources/p008_digit.txt"))))))

(deftest problem-009
  (is (= (* 3 4 5) (e001/p009 (+ 3 4 5))))
  (is (= (* 200 375 425) (e001/p009 1000))))

(deftest Problem-010
  (is (= 17 (e001/p010 10)))
  (is (= 142913828922 (e001/p010 2000000))))
