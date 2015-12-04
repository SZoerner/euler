(ns euler.euler031
  (:require [euler.helper :as helper]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 31 - Coin sums
;;
;; **Description:** In England the currency is made up of pound, £,
;; and pence, p, and there are eight coins in general circulation:
;;
;; 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
;; It is possible to make £2 in the following way:
;;
;; 1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn p031
  "**Task:** How many different ways can £2 be made using any number of coins?"
  ([] (p031 200 [200 100 50 20 10 5 2 1]))
  ([n coins] (helper/combinations n coins)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 32 - Pandigital products
;;
;; **Description:** We shall say that an n-digit number is pandigital
;; if it makes use of all the digits 1 to n exactly once; for example,
;; the 5-digit number, 15234, is 1 through 5 pandigital.
;; The product 7254 is unusual, as the identity, 39 × 186 = 7254,
;; containing multiplicand, multiplier, and product is 1 through 9 pandigital.
;;
;; Find the sum of all products whose multiplicand/multiplier/product identity
;; can be written as a 1 through 9 pandigital.
;; HINT: Some products can be obtained in more than one way so be sure
;; to only include it once in your sum.
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;; There are only two patterns of multiplications that produce 9 digits:
;; 1. A 1 digit number times a 4 digit number can be 4 digits long.
;; 2. A 2 digit number times a 3 digit number can be 4 digits long.

(defn- pandigital?
  "Makes use of all the digits 1 to n exactly once."
  [a b] (= "123456789"
           (clojure.string/join (sort (.split (str a b (* a b)) "")))))

(defn p032
  "Find the sum of all products whose multiplicand/multiplier/product identity
  can be written as a 1 through 9 pandigital."
  [] (reduce + (distinct (for [a (range 2 5000)
                               b (range a (/ 9999 a))
                               :when (pandigital? a b)]
                           (* a b)))))
