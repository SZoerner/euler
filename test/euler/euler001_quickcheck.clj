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