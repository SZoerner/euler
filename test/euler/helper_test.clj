(ns euler.helper-test
  (:require [clojure.test :refer [deftest is]]
            [euler.helper :as helper]))

(deftest prime-factors-of
  (is (= [2 2 3] (helper/prime-factors-of 12)))
  (is (= [3 41] (helper/prime-factors-of 123))))

(deftest amicable?
  (is (= [220 284] (helper/amicable? 220)))
  (is (= [284 220] (helper/amicable? 284))))

(deftest abundant?
  (is (helper/abundant? 12)))

(deftest abundant-sum?
  (is (not (helper/abundant-sum? 12)))
  (is (helper/abundant-sum? 24)))

(deftest triangle
  (is (= 1 (last (take 1 helper/triangles))))
  (is (= 55 (last (take 10 helper/triangles))))
  (is (= 5050 (last (take 100 helper/triangles)))))

(deftest parse-grid
  (is (= [[1 2] [3 4]] (helper/parse-grid "1 2 3 4" 2)))
  (is (= [[1 2 3] [4 5 6] [7 8 9]] (helper/parse-grid "1 2 3 4 5 6 7 8 9" 3))))

(deftest collatz
  (is (= [12 6 3 10 5 16 8 4 2 1] (helper/collatz 12))))

(deftest to-words
  (is (= "onehundred" (helper/to-words 100)))
  (is (= "onehundredandfifteen" (helper/to-words 115)))
  (is (= "threehundredandfortytwo" (helper/to-words 342))))

(deftest prime?
  (is (helper/prime? 2))
  (is (not (helper/prime? 12))))
