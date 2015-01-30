(ns euler.test-test
(:require [clojure.test.check.generators :as gen]
		  [euler.core-test :refer :all]
		  [euler.euler001 :refer :all])
(:use midje.sweet))

(fact "test 1"
	(p001 5) => 3)

(fact-quickcheck "Test 2"
 [n gen/nat]
   (p002 n) => even?)