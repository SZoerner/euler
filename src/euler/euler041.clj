(ns euler.euler041
  (:require [euler.helper :as helper]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 41 - Pandigital prime
;;
;; We shall say that an n-digit number is pandigital if it makes use of all the
;; digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital and is
;; also prime. What is the largest n-digit pandigital prime that exists?
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn p041
  "What is the largest n-digit pandigital prime that exists?"
  [] (->> (for [x (range 1 10)] (apply str (range 1 (inc x))))
          (mapcat helper/permutations)
          (map #(read-string (apply str %)))
          (filter helper/prime?)
          (apply max)))
