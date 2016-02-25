(ns euler.euler031-test
  (:require [clojure.test :refer [deftest]]
            [euler.euler031 :as e031]
            [midje.sweet :refer [fact]]))

(deftest euler031-tests
  (fact "Problem 31"
        (e031/p031 1 [1]) => 1
        (e031/p031 3 [1 2]) => 2
        (e031/p031 5 [1 2]) => 3
        (e031/p031) => 73682)
  (fact "Problem 32"
        (e031/p032) => 45228)
  (fact "Problem 33"
        (e031/p033) => 100)
  (fact "Problem 34"
        (e031/p034) => 40730)
  (fact "Problem 35"
        (e031/p035) => 55)
  (fact "Problem 36"
        (e031/p036) => 872187)
  (fact "Problem 37"
        (e031/p037) => 748317))