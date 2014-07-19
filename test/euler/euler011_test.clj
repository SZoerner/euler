(ns euler.euler011-test
  (:require [clojure.test :refer :all]
            [euler.euler011 :refer :all]))

(deftest prob-012-test
  (testing "Problem 12"    
    (is (= 28 (prob-012 6)))))