(ns euler.euler001-test
  (:require [clojure.test :refer :all]
            [euler.euler001 :refer :all]))

(deftest prob-001-test
  (testing "Problem 1"
    (is (= 23 (prob-001 10)))
    (is (= 3 (prob-001 5)))))

(deftest prob-002-test
  (testing "Problem 2"
    (is (= 44 (prob-002 35)))))

(deftest prob-005-test
  (testing "Problem 5"
    (is (= 2520 (prob-005 10)))))

(deftest prob-006-test
  (testing "Problem 6"
    (is (= (prob-006 10) 2640))))
