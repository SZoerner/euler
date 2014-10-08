(ns euler.euler001-test
  (:require [clojure.test :refer :all]
            [euler.euler001 :refer :all]))

(deftest p001-test
  (testing "Problem 1"
    (is (= 3 (p001 5)))
    (is (= 23 (p001 10)))
    (is (= 233168 (p001 1000)))))

(deftest p002-test
  (testing "Problem 2"
    (is (= 44 (p002 35)))
    (is (= 4613732 (p002 4000000)))))

(deftest p002-lazy-test
  (testing "Problem 2"
    (is (= 44 (p002-lazy 35)))
    (is (= 4613732 (p002-lazy 4000000)))))

(deftest p003-test
  (testing "Problem 3"
    (is (= 29 (p003 13195)))
    (is (= 6857 (p003 600851475143)))))

(deftest p003-lazy-test
  (testing "Problem 3"
    (is (= 29 (p003-lazy 13195)))
    (is (= 6857 (p003-lazy 600851475143)))))

(deftest p004-test
  (testing "Problem 4"
    (is (= 9009 (p004 100)))))

(deftest p005-test
  (testing "Problem 5"
    (is (= 2520 (p005 10)))
    (is (= 232792560 (p005 20)))))

(deftest p006-test
  (testing "Problem 6"
    (is (= 2640 (p006 10)))
    (is (= 25164150 (p006 100)))))

(deftest p007-test
  (testing "Problem 7"
    (is (= 13 (p007 6)))
    (is (= 104743 (p007 10001)))))

(deftest p008-test
  (testing "Problem 8"
    (is (= 15120 (p008 5 123456789)))))

(deftest p009-test
  (testing "Problem 9"
    (is (= (* 3 4 5) (p009 (+ 3 4 5))))
    (is (= (* 200 375 425) (p009 1000)))))

(deftest p010-test
  (testing "Problem 10"
    (is (= 17 (p010 10)))
    (is (= 142913828922 (p010 2000000)))))