(ns euler.euler011-test
  (:require [clojure.test :refer [deftest is]]
            [euler.euler011 :as e011]))

(deftest problem-011
  (is (= (e011/p011) 70600674)))

(deftest problem-012
  (is (= (e011/p012) 76576500)))

(deftest problem-013
  (is (= (e011/p013) 5537376230342)))

(deftest problem-014
  (is (= (e011/p014 10) 9))
  (is (= (e011/p014 100) 97))
  (is (= (e011/p014 1000) 871))
  (is (= (e011/p014) 837799)))

(deftest problem-015
  (is (= (e011/p015) 137846528820)))

(deftest problem-016
  (is (= (e011/p016) 1366)))

(deftest problem-017
  (is (= (e011/p017 10) 39))
  (is (= (e011/p017) 21124)))

(deftest problem-018
  (is (= (e011/p018) 1074)))

(deftest problem-019
  (is (= (e011/p019) 171)))

(deftest problem-020
  (is (= (e011/p020) 648)))
