(ns euler.euler001-quickcheck
  (:require [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct :refer (defspec)]
            [euler.euler001 :refer :all]))

(defspec prob-002-quickcheck ; the name of the test
  100 ; the number of iterations for test.check to test
  (prop/for-all [n gen/nat]
                (even? (prob-002 n)))) ; always returns an even result

(defspec prob-002-lazy-quickcheck
  100
  (prop/for-all [n gen/nat]
                (= (prob-002 n) (prob-002-lazy n)))) ; both algorithms return the same results

(defspec prob-003-lazy-quickcheck
  100
  (prop/for-all [n (gen/such-that #(> % 1) gen/nat)]
                (= (prob-003 n) (prob-003-lazy n)))) ; both algorithms return the same results

(defspec prob-004-quickcheck
  100 
  (prop/for-all [n (gen/such-that #(> % 1) gen/nat)]
                (let [res (str (prob-004 n))]
                  (= res (apply str (reverse res)))))) ; each returned value is indeed a palindrome

(defspec prob-005-quickcheck
  100
  (prop/for-all [n (gen/elements (vec (range 23)))]
                (let [res (prob-005 n)
                      nums (range 2 (+ 1 n))
                      div-by? (fn [x] (= 0 (rem res x)))]
                  (every? div-by? nums))))