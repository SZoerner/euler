(ns euler.euler031-test
  (:require [clojure.test :refer [deftest]]
            [clojure.test.check.generators :as gen]
            [euler.core-test :refer :all]
            [euler.euler031 :refer :all]
            [midje.sweet :refer :all]))

(deftest euler031-tests
  (fact "Problem 31"
        (p031 1 [1]) => 1
        (p031 3 [1 2]) => 2
        (p031 5 [1 2]) => 3
        (p031) => 73682))