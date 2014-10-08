(ns euler.euler011-test
  (:require [clojure.test :refer :all]
            [euler.euler011 :refer :all]))

(deftest p011-test
  (testing "Problem 11"
    (is (= 70600674 (p011)))))

(deftest p012-test
  (testing "Problem 12"
    (is (= 76576500 (p012)))))

(deftest p013-test
  (testing "Problem 13"
    (is (= 5537376230342 (p013)))))

(deftest p014-test
  (testing "Problem 14"
    (is (= 837799 (p014)))))

(deftest p015-test
  (testing "Problem 15"
    (is (= 137846528820 (p015)))))

(deftest p016-test
  (testing "Problem 16"
    (is (= 1366 (p016)))))

(deftest p017-test
  (testing "Problem 17"
    (is (= 21124 (p017)))))

(deftest p018-test
  (testing "Problem 18"
    (is (= 1074 (p018)))))

(deftest p019-test
  (testing "Problem 19"
    (is (= 171 (p019)))))

(deftest p020-test
  (testing "Problem 20"
    (is (= 648 (p020)))))