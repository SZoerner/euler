(ns euler.euler001-test
  (:require [clojure.edn :as edn]
            [clojure.test.check.generators :as gen]
            [clojure.test :refer [deftest]]
            [euler.core-test :refer [fact-qc]]
            [euler.euler001 :as e001]
            [midje.sweet :refer [fact]]))

(deftest euler001-tests
  (fact "Problem 1"
        (e001/p001 5 3 5) => 3
        (e001/p001 10 3 5) => 23
        (e001/p001) => 233168)

  (fact "Problem 2"
        (e001/p002 35) => 44
        (e001/p002) => 4613732
        (fact-qc "always returns an even result"
                 [n gen/nat]
                 (e001/p002 n) => even?))

  (fact "Problem 3"
        (e001/p003 13195) => 29
        (e001/p003) => 6857)

  (fact "Problem 4"
        (e001/p004 100) => 9009
        (e001/p004) => 906609
        (fact-qc "each returned value is indeed a palindrome"
                 [n (gen/such-that #(> % 1) gen/nat)]
                 (let [res (str (e001/p004 n))]
                   res => (apply str (reverse res)))))

  (fact "Problem 5"
        (e001/p005 10) => 2520
        (e001/p005) => 232792560
        (fact-qc "e001/p005-quickcheck"
                 [n gen/int]
                 (let [res (e001/p005 n)
                       nums (range 2 (+ 1 n))
                       not-div-by? (fn [x] (not (= 0 (rem res x))))]
                   (filter not-div-by? nums) => '())))

  (fact "Problem 6"
        (e001/p006 10) => 2640
        (e001/p006) => 25164150)

  (fact "Problem 7"
        (e001/p007 6) => 13
        (e001/p007 10) => 29
        (e001/p007) => 104743)

  (fact "Problem 8"
        (e001/p008 5 123456789) => 15120
        (e001/p008) => 40824)

  (fact "Problem 9"
        (e001/p009 (+ 3 4 5)) => (* 3 4 5)
        (e001/p009) => (* 200 375 425)
        (fact-qc "all Pythagorean triplets up to 300"
                 [[a b c] (->> "resources/p009_triplets.txt"
                               slurp
                               edn/read-string
                               gen/elements)]
                 (= (* a b c) (e001/p009 (+ a b c)))))

  (fact "Problem 10"
        (e001/p010 10) => 17
        (e001/p010) => 142913828922))
