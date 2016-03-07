(ns euler.gen-test
  (:require [clojure.edn :as edn]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [euler.euler001 :as e001]
            [euler.helper :as helper]))

(defspec p002 100
  (prop/for-all [n gen/nat] ;; always returns an even result
                (= (even? (e001/p002 n)) true)))

(defspec p004 100
  (prop/for-all [n (gen/such-that #(> % 1) gen/nat)]
                (let [res (str (e001/p004 n))]
                ;; each returned value is indeed a palindrome
                  (= res (apply str (reverse res))))))

(defspec p005 100
  (prop/for-all [n (gen/such-that #(> 23 %) gen/int)]
                (let [res (e001/p005 n)
                      nums (range 2 (+ 1 n))
                      not-div-by? (fn [x] (not (= 0 (rem res x))))]
                  (= (filter not-div-by? nums) '()))))

(defspec p009 100
  ;; all Pythagorean triplets up to 300
  (prop/for-all [[a b c] (->> "resources/p009_triplets.txt"
                              slurp
                              edn/read-string
                              gen/elements)]
                (= (* a b c) (e001/p009 (+ a b c)))))

(defspec factors-vs-count-divisors 100
  (prop/for-all [n gen/nat]
                (= (count (helper/factors n)) (helper/count-divisors n))))

(defspec parse-grid-vector-of-vectors 100
  (prop/for-all [n gen/nat]
                (= (helper/parse-grid (str n) 1) [[n]])))
