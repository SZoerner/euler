(ns euler.euler011-test
  (:require [clojure.test :refer :all]
            [euler.euler011 :refer :all]))

(deftest prob-011-test
  (testing "Problem 11"
    (is (= 70600674 (prob-011)))))

(deftest prob-012-test
  (testing "Problem 12"
    (is (= 76576500 (prob-012)))))

(deftest prob-013-test
  (testing "Problem 13"
    (is (= 5537376230342 (prob-013)))))

(deftest prob-014-test
  (testing "Problem 14"
    (is (= 837799 (prob-014)))))

(deftest prob-015-test
  (testing "Problem 15"
    (is (= 137846528820 (prob-015)))))

(deftest prob-016-test
  (testing "Problem 16"
    (is (= 1366 (prob-016)))))

(deftest prob-017-test
  (testing "Problem 17"
    (is (= 21124 (prob-017)))))

(deftest prob-018-test
  (testing "Problem 18"
    (is (= 1074 (prob-018)))))

(deftest prob-019-test
  (testing "Problem 19"
    (is (= 171 (prob-019)))))

(deftest prob-020-test
  (testing "Problem 20"
    (is (= 648 (prob-020)))))