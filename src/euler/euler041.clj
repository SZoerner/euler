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


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 42 - Coded triangle numbers
;;
;; The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1);
;; so the first ten triangle numbers are: 1, 3, 6, 10, 15, 21, 28, 36, 45, 55.
;;
;; By converting each letter in a word to a number corresponding to its
;; alphabetical position and adding these values we form a word value.
;; For example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word
;; value is a triangle number then we shall call the word a triangle word.
;;
;; Using words.txt, a 16K text file containing nearly two-thousand common
;; English words, how many are triangle words?
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn word-value
  [s] (->> s
           seq
           (map #(- (int %) 64))
           (reduce +)))

(defn p042
  "How many are triangle words?"
  [] (->> "resources/p042_words.txt"
          slurp
          (re-seq #"\w+")
          (map word-value)
          (filter helper/triangle?)
          count))
