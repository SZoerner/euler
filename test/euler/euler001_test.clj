(ns euler.euler001-test
  (:require [clojure.test :refer :all]
            [euler.euler001 :refer :all]))

(deftest prob-001-test
  (testing "Problem 1"    
    (is (= 3 (prob-001 5)))
    (is (= 23 (prob-001 10)))
    (is (= 233168 (prob-001 1000)))))

(deftest prob-002-test
  (testing "Problem 2"
    (is (= 44 (prob-002 35)))
    (is (= 4613732 (prob-002 4000000)))))

(deftest prob-002-lazy-test
  (testing "Problem 2"
    (is (= 44 (prob-002-lazy 35)))
    (is (= 4613732 (prob-002-lazy 4000000)))))

(deftest prob-003-test
  (testing "Problem 3"
    (is (= 29 (prob-003 13195)))
    (is (= 6857 (prob-003 600851475143)))))

(deftest prob-003-lazy-test
  (testing "Problem 3"
    (is (= 29 (prob-003-lazy 13195)))
    (is (= 6857 (prob-003-lazy 600851475143)))))
        
(deftest prob-004-test
  (testing "Problem 4"
    (is (= 9009 (prob-004 100)))))        

(deftest prob-005-test
  (testing "Problem 5"
    (is (= 2520 (prob-005 10)))))

(deftest prob-006-test
  (testing "Problem 6"
    (is (= 2640 (prob-006 10)))))

(deftest prob-007-test
  (testing "Problem 7"
    (is (= 13 (prob-007 6)))))
        
(deftest prob-008-test
  (testing "Problem 8"
    (is (= 15120 (prob-008 5 123456789)))))

(deftest prob-009-test
  (testing "Problem 9"
    (is (= (* 3 4 5)(prob-009 (+ 3 4 5))))))

(deftest prob-010-test
  (testing "Problem 10"
    (is (= 17 (prob-010 10)))))