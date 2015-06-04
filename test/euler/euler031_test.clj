(ns euler.euler031-test
  (:require [clojure.test :refer [deftest]]
            [midje.sweet :refer [fact]]
            [euler.euler031 :as e031]))

(deftest euler031-tests
  (fact "Problem 31"
        (e031/p031 1 [1]) => 1
        (e031/p031 3 [1 2]) => 2
        (e031/p031 5 [1 2]) => 3
        (e031/p031) => 73682))
