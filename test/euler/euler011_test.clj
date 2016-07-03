(ns euler.euler011-test
  (:require [clojure.test :refer [deftest is]]
            [euler.euler011 :as e011]))

(deftest problem-011
  (is (= 70600674 (e011/p011))))

(deftest problem-012
  (is (= 76576500 (e011/p012))))

(deftest problem-013
  (is (= 5537376230342 (e011/p013))))

(deftest problem-014
  (is (= 9 (e011/p014 10)))
  (is (= 97 (e011/p014 100)))
  (is (= 871 (e011/p014 1000)))
  (is (= 837799 (e011/p014))))

(deftest problem-015
  (is (= 137846528820 (e011/p015))))

(deftest problem-016
  (is (= 1366 (e011/p016))))

(deftest problem-017
  (is (= 39 (e011/p017 10)))
  (is (= 21124 (e011/p017))))

(deftest problem-018
  (is (= 1074 (e011/p018))))

(deftest problem-019
  (is (= 171 (e011/p019))))

(deftest problem-020
  (is (= 648 (e011/p020))))
