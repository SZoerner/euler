(ns euler.euler011
  (:use euler.helper))

;; # Problem 11 - Largest product in a grid
;;
;; *Description:* 
;; In the 20x20 grid below, four numbers along a diagonal line have been marked in red.
;; The product of these numbers is ``26 x 63 x 78 x 14 = 1788696``.
;; 
;; **Task:** 
;; What is the greatest product of four adjacent numbers in the same direction
;; - up, down, left, right, or diagonally - in the 20x20 grid?

(defn p011
  ([] (p011 20 4 (slurp "resources/p011_matrix.txt")))
  ([size len input-str] (product size len (parse-grid input-str size))))


;; # Problem 12 - Highly divisible triangular number
;; 
;; **Task:** 
;; What is the value of the first triangle number to have over five hundred divisors?

(defn p012
  "*Int -> Int*  
  Value of the first triangle number up to n."
  ([] p012 500)
  ([n]
   (loop [cnt 1]
     (let [cache (triangle cnt)]
       (if (>= (num-of-divisors cache) n) cache
           (recur (inc cnt)))))))


;; # Problem 13 - Large sum
;; 
;; **Task:** 
;; Work out the first ten digits of the sum of the following one-hundred 50-digit numbers.

(defn p013
  "*[Int] -> Int*  
  The sum of the first n digits of the given list of numbers."
  ([] (p013 10 "resources/p013_numbers.txt"))
  ([n input]
   (long (reduce + (map #(truncate % n) (map bigdec (re-seq #"\d+" (slurp input))))))))


;; # Problem 14 - Longest Collatz sequence
;; 
;; **Description:** 
;; The following iterative sequence is defined for the set of positive integers:
;; n → n/2 (n is even),  n → 3n + 1 (n is odd)
;;
;; Using the rule above and starting with 13, we generate the following sequence:
;; 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
;; It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms.
;; Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
;; 
;; **Task:**
;; Which starting number, under one million, produces the longest chain?

(defn p014 
  ([] (p014 (Math/pow 10 6)))
  ([limit] 
    (let [cache (atom {1 1})]
      (doseq [n (range 1 limit)] (memo-collatz cache n))
      (key (apply max-key val @cache)))))


;; # Problem 15 - Lattice paths
;; 
;; **Description:** Starting in the top left corner of a 2×2 grid, 
;; and only being able to move to the right and down,
;; there are exactly 6 routes to the bottom right corner. 
;;
;; **Task:**
;; How many such routes are there through a 20×20 grid?

;; reference: http://www.robertdickau.com/manhattan.html
;; The number of paths are the central binomial coefficients,
;; Binomial[2n, n] or (2n)!/(n!)^2,
;; central meaning they fall along the center line of Pascal’s triangle.

(defn p015 
  ([] (p015 20))
  ([n] (lattice-paths n)))


;; # Problem 16 - Power digit sum
;; 
;; **Description:** 
;; 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
;; 
;; **Task:**
;; What is the sum of the digits of the number 2^1000?

(defn p016 
  ([] (p016 (.pow (BigInteger. "2") 1000)))
  ([n] (reduce + (digits n))))


;; # Problem 17 - Number letter counts
;; 
;; **Description:** 
;; If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
;; 
;; **Task:**
;; If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?
;; NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 20 letters.
;; The use of "and" when writing out numbers is in compliance with British usage.

(defn p017 
  ([] (p017 1000))
  ([n] (->> (range 1 (inc n))
          ;; map to corresponding string
         (map to-words)
          ;; concatenate
         (apply str)
          ;; count the number of chars
         (count))))


;; # Problem 18 - Maximum path sum I
;; 
;; **Description:**  
;;       3      By starting at the top of the triangle left
;;      7 4     and moving to adjacent numbers on the row below,
;;     2 4 6
;;    8 5 9 3
;;
;;
;; the maximum total from top to bottom is 23.
;; That is, 3 + 7 + 4 + 9 = 23.
;; 
;; **Task:**
;; Find the maximum total from top to bottom of the triangle below:

(defn p018 []
  (let
   [triangle
    [[75]
     [95 64]
     [17 47 82]
     [18 35 87 10]
     [20 4 82 47 65]
     [19 1 23 75 3 34]
     [88 2 77 73 7 63 67]
     [99 65 4 28 6 16 70 92]
     [41 41 26 56 83 40 80 70 33]
     [41 48 72 33 47 32 37 16 94 29]
     [53 71 44 65 25 43 91 52 97 51 14]
     [70 11 33 28 77 73 17 78 39 68 17 57]
     [91 71 52 38 17 14 91 43 58 50 27 29 48]
     [63 66 4 68 89 53 67 30 73 16 69 87 40 31]
     [04 62 98 27 23 9 70 98 73 93 38 53 60 4 23]]

    merge-rows
    (fn [a b]
       ;; look at the two elements in the row below it,
       ;; take the max of those two elements, and sum them to the original element.
      (map + (map #(reduce max %) (partition 2 1 a)) b))]
    (first (reduce merge-rows (reverse triangle)))))


;; # Problem 19 - Counting Sundays
;; 
;; **Description:** 
;; You are given the following information, but you may prefer to do some research for yourself.
;;
;; 1 Jan 1900 was a Monday. Thirty days has September, April, June and November.
;; All the rest have thirty-one, Saving February alone, Which has twenty-eight, rain or shine.
;; And on leap years, twenty-nine.
;; A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
;; 
;; **Task:**
;; How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?

;; by hroi
;; http://clojure-euler.wikispaces.com/Problem+019

(defn p019 []
  (count
   (for [year (range 1 101) month (range 0 12)
         :let [day (.getDay (doto (java.util.Date.) (.setYear year)
                                  (.setMonth month) (.setDate 1)))]
         :when (zero? day)]
     [year, month])))


;; # Problem 20 - Factorial digit sum
;; 
;; **Description:** 
;; n! means n × (n − 1) × ... × 3 × 2 × 1
;; For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
;; and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
;; 
;; **Task:**
;; Find the sum of the digits in the number 100!

;; analogous to Problem 16
(defn p020 
  ([] (p020 100))
  ([n] (reduce + (digits (factorial n)))))
