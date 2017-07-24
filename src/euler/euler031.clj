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

(defn p032
  "Find the sum of all products whose multiplicand/multiplier/product identity
  can be written as a 1 through 9 pandigital."
  [] (reduce + (distinct (for [a (range 2 5000)
                               b (range a (/ 9999 a))
                               :let [prod (* a b)]
                               :when (helper/pandigital? (str a b prod))]
                           prod))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 33 - Digit cancelling fractions
;;
;; The fraction 49/98 is a curious fraction, as an inexperienced mathematician
;; in attempting to simplify it may incorrectly believe that 49/98 = 4/8,
;; which is correct, is obtained by cancelling the 9s.
;;
;; We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
;; There are exactly four non-trivial examples of this type of fraction,
;; less than one in value, with two digits in the numerator and denominator.
;;
;; If the product of these four fractions is given in its lowest common terms,
;; find the value of the denominator.
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn p033
  "Find the denominator of the four non-trivial examples."
  [] (denominator (reduce * (for [a (range 1 10)
                                  b (range 1 10)
                                  c (range 1 10)
                                  :let [num (+ (* 10 a) b)
                                        den (+ (* 10 b) c)
                                        divisor (/ num den)]
                                  :when (and (< divisor 1)
                                             (= divisor (/ a c)))]
                              (/ num den)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 34 - Digit factorials
;;
;; 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
;; Find the sum of all numbers which are equal to the sum of the factorial of
;; their digits. Note: as 1! = 1 and 2! = 2 are not sums they are not included.
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def ^:private fact
  "Caching the factorials of the digits 0 to 9"
  (apply hash-map (interleave (seq "0123456789")
                              (map helper/factorial
                                   (range 10)))))
(defn- curious?
  "A number that is equal to the sum of the factorial of its digits."
  [n] (->> n
           (str)
           (map fact)
           (reduce +)
           (= n)))

(defn p034
  "Find the sum of all numbers which are equal to
  the sum of the factorial of their digits."
  [] (->> (range 10 (* 7 (helper/factorial 9)))
          (filter curious?)
          (reduce +)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 35 - Circular primes
;;
;; The number, 197, is called a circular prime because all rotations of
;; the digits: 197, 971, and 719, are themselves prime. There are thirteen such
;; primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
;; How many circular primes are there below one million?
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn- circular-prime?
  "A prime is circular if all rotations of the digits are themselves prime."
  [n] (->> (str n)
           (helper/rotations)
           (map #(clojure.string/join %))
           (map #(Integer/parseInt %))
           (every? helper/prime?)))

(defn p035
  "How many circular primes are there below one million?"
  [] (->> helper/primes
          (take-while #(< % (Math/pow 10 6)))
          (filter circular-prime?)
          (count)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 36 - Double-base palindromes
;;
;; The decimal number, 585 = 1001001001 (bin), is palindromic in both bases.
;; Find the sum of all numbers, less than one million, which are palindromic
;; in base 10 and base 2. (Please note that the palindromic number, in either
;; base, may not include leading zeros.)
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn- dec->bin
  "Convert decimal Integers into binary representation."
  [n] (if (<= 0 n 1) n
          (+' (*' 10 (dec->bin (quot n 2))) (mod n 2))))

(defn p036
  "Find the sum of all numbers, less than one million,
  which are palindromic in base 10 and base 2."
  [] (->> (range 1 (Math/pow 10 6))
          (filter helper/palindrome?)
          (filter (comp helper/palindrome? dec->bin))
          (reduce +)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 37 - Truncatable primes
;;
;; The number 3797 has an interesting property. Being prime itself,
;; it is possible to continuously remove digits from left to right,
;; and remain prime at each stage: 3797, 797, 97, and 7.
;;
;; Similarly we can work from right to left: 3797, 379, 37, and 3.
;; Find the sum of the only eleven primes that are both truncatable from left
;; to right and right to left.
;; NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn- remove-first-digit
  "'rest' for Integers."
  [n] (->> n Math/log10 int (Math/pow 10) int (mod n)))

(defn- remove-last-digit
  "'butlast' for Integers."
  [n] (int (/ n 10)))

(defn- truncatable-prime?
  "Being prime itself, it is possible to continuously remove digits from left
  to right as well as from right to left, and remain prime at each stage."
  [n] (let [trunc-prime? (fn [f n] (cond
                                     (< n 10) (helper/prime? n)
                                     (helper/prime? n) (recur f (f n))
                                     true false))]
        (and (trunc-prime? remove-last-digit n)
             (trunc-prime? remove-first-digit n))))

(defn p037
  "Find the sum of the only eleven primes that are both truncatable
  from left to right and right to left."
  [] (reduce + (take 11 (filter truncatable-prime? (iterate inc 10)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 38 - Pandigital multiples
;;
;; Take the number 192 and multiply it by each of 1, 2, and 3:
;; 192 × 1 = 192, 192 × 2 = 384, 192 × 3 = 576.
;;
;; By concatenating each product we get the 1 to 9 pandigital, 192384576.
;; We will call 192384576 the concatenated product of 192 and (1,2,3).
;; The same can be achieved by starting with 9 and multiplying by
;; 1, 2, 3, 4, and 5, giving the pandigital, 918273645, which is the
;; concatenated product of 9 and (1,2,3,4,5).
;;
;; What is the largest 1 to 9 pandigital 9-digit number that can be formed as
;; the concatenated product of an integer with (1,2, ... , n) where n > 1?
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn concat-product [n upper]
  (->> (range 1 (inc upper))
       (map #(* n %))
       (mapcat str)
       (apply str)
       (read-string)))

(defn p038
  "What is the largest 1 to 9 pandigital 9-digit number that can be formed as
  the concatenated product of an integer with (1,2, ... , n) where n > 1?"
  [] (apply max
            (for [r (range 1 10)
                  n (range 1 9999)
                  :let [product (concat-product n r)]
                  :when (and (< product 987654321) (helper/pandigital? product))]
              product)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 39 - Integer right triangles
;;
;; If p is the perimeter of a right angle triangle with integral length sides,
;; {a,b,c}, there are exactly three solutions for p = 120.
;;
;; {20,48,52}, {24,45,51}, {30,40,50}
;;
;; For which value of p ≤ 1000, is the number of solutions maximised?
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn integer-right-triangles [n]
  (for [a (range 1 500)
        b (range a 500)
        :let [c (- n a b)]
        :when (and (every? pos? [a b c])
                   (= (+ (* a a) (* b b)) (* c c)))]
    [a b c]))

(defn p039
  "For which value of p ≤ 1000, is the number of solutions maximised?"
  [n] (->> (range 1 (inc n))
          (map integer-right-triangles)
          (map #(vector (apply + (first %)) (count %) %))
          (sort-by second >)
          first))
