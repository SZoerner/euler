(ns euler.helper-test
  (:require [clojure.test :refer [deftest is]]
            [euler.helper :as helper]))

(deftest prime-factors
  (is (= (helper/prime-factors 12) '(2 2 3)))
  (is (= (helper/prime-factors 123) '(3 41))))

(deftest amicable?
  (is (= (helper/amicable? 220) [220 284]))
  (is (= (helper/amicable? 284) [284 220])))

(deftest abundant?
  (is (= (helper/abundant? 12) true)))

(deftest abundant-sum?
  (is (= (helper/abundant-sum? 12) false))
  (is (= (helper/abundant-sum? 24) true)))

(deftest triangle
  (is (= (last (take 1 helper/triangles)) 1))
  (is (= (last (take 10 helper/triangles)) 55))
  (is (= (last (take 100 helper/triangles)) 5050)))

(deftest parse-grid
  (is (= (helper/parse-grid "1 2 3 4" 2) [[1 2] [3 4]]))
  (is (= (helper/parse-grid "1 2 3 4 5 6 7 8 9" 3) [[1 2 3] [4 5 6] [7 8 9]])))

(deftest collatz
  (is (= (helper/collatz 12) '(12 6 3 10 5 16 8 4 2 1))))

(deftest to-words
  (is (= (helper/to-words 100) "onehundred"))
  (is (= (helper/to-words 115) "onehundredandfifteen"))
  (is (= (helper/to-words 342) "threehundredandfortytwo")))

(deftest prime?
  (is (= (helper/prime? 2) true))
  (is (= (helper/prime? 12) false)))

(deftest narcissistic?
  (is (= (helper/narcissistic? 1634 4) true)))
