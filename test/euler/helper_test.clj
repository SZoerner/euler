(ns euler.helper-test
  (:require [clojure.test :refer [deftest]]
            [clojure.test.check.generators :as gen]
            [euler.core-test :refer :all]
            [euler.helper :refer :all])
  (:use midje.sweet))

(deftest helper-tests
  (fact "prime-factors"
        (prime-factors 12) => '(2 2 3)
        (prime-factors 123) => '(3 41)))