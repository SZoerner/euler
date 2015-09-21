(ns euler.gen-test
  (:require [clojure.edn :as edn]
            [clojure.test :refer [deftest]]
            [clojure.test.check.generators :as gen]
            [euler.core-test :refer [fact-qc]]
            [euler.euler001 :as e001]
            [euler.helper :as helper]))

(deftest generative-tests

  (fact-qc "Problem 2: always returns an even result"
           [n gen/nat]
           (e001/p002 n) => even?)

  (fact-qc "Problem 4: each returned value is indeed a palindrome"
           [n (gen/such-that #(> % 1) gen/nat)]
           (let [res (str (e001/p004 n))]
             res => (apply str (reverse res))))

  (fact-qc "Problem 5: e001/p005-quickcheck"
           [n gen/int]
           (let [res (e001/p005 n)
                 nums (range 2 (+ 1 n))
                 not-div-by? (fn [x] (not (= 0 (rem res x))))]
             (filter not-div-by? nums) => '()))

  (fact-qc "Problem 9: all Pythagorean triplets up to 300"
           [[a b c] (->> "resources/p009_triplets.txt"
                         slurp
                         edn/read-string
                         gen/elements)]
           (= (* a b c) (e001/p009 (+ a b c))))

  (fact-qc "factors vs count-divisors"
           [n gen/nat]
           (count (helper/factors n)) => (helper/count-divisors n))

  (fact-qc "factors vs count-divisors"
           [n gen/nat]
           (count (helper/factors n)) => (helper/count-divisors n))

  (fact-qc "parse-grid: return a vector of vectors"
           [n gen/nat]
           (helper/parse-grid (str n) 1) => [[n]]))
