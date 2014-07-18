(ns euler.euler001-test
  (:require [clojure.test :refer :all]
            [euler.euler001 :refer :all]))

(deftest prob-001-test
  (testing "problem 1."
    (is (= 23 (prob-001 10)))
    (is (= 3 (prob-001 5)))))
