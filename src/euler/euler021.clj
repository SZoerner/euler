(ns euler.euler021
  (:require [euler.helper :as helper]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 21 - Amicable numbers
;;
;; **Description:** Let d(n) be defined as the sum of proper divisors of n
;; (numbers less than n which divide evenly into n). If d(a) = b and d(b) = a,
;; where a ≠ b, then a and b are an amicable pair and each of a and b are
;; called amicable numbers. For example, the proper divisors of 220 are
;; 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284.
;; The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn p021
  "**Task:** Evaluate the sum of all the amicable numbers under 10000."
  ([] (p021 10000))
  ([n] (->> (range 1 (inc n))
            (map helper/amicable?)
            (flatten)
            (distinct)
            (reduce +))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 22 - Names scores
;;
;; **Description:** Using 'http://projecteuler.net/project/names.txt',
;; a 46K text file containing over five-thousand first names, begin by sorting
;; it into alphabetical order. Then working out the alphabetical value for each
;; name, multiply this value by its alphabetical position in the list to obtain
;; a name score. For example, when the list is sorted into alphabetical order,
;; COLIN, worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list.
;; So, COLIN would obtain a score of 938 × 53 = 49714.
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn p022
  "**Task:** What is the total of all the name scores in the file?"
  ([] (p022 (re-seq #"\w+" (slurp "resources/p022_names.txt"))))
  ([names]
   (let
    [input (sort names)
     ;; sum of the numerical representation of each character
     get-value (fn [name] (reduce + (map #(- (int %) 64) name)))
     ;; get the position
     get-index (fn [name] (inc (.indexOf input name)))]
      ;; compute the product of value and position
     (reduce + (map #(* (get-index %) (get-value %)) input)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 23 - Non-abundant sums
;;
;; **Description:** A perfect number is a number for which the sum of its
;; proper divisors is exactly equal to the number. For example, the sum of the
;; proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28
;; is a perfect number. A number n is called deficient if the sum of its proper
;; divisors is less than n and it is called abundant if this sum exceeds n.
;;
;; As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest
;; number that can be written as the sum of two abundant numbers is 24. By
;; mathematical analysis, it can be shown that all integers greater than 28123
;; can be written as the sum of two abundant numbers. However, this upper limit
;; cannot be reduced any further by analysis even though it is known that the
;; greatest number that cannot be expressed as the sum of two abundant numbers
;; is less than this limit.
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn p023
  "**Task:** Find the sum of all the positive integers which cannot be written
  as the sum of two abundant numbers."
  ([] (p023 (range 1 (inc 28123))))
  ([input] (->> input
                (remove helper/abundant-sum?)
                (reduce +))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 24 - Lexicographic permutations
;;
;; **Description:** A permutation is an ordered arrangement of objects.
;; For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4.
;; If all of the permutations are listed numerically or alphabetically,
;; we call it lexicographic order. The lexicographic permutations of
;; 0, 1 and 2 are: 012 021 102 120 201 210.
;;
;;
;; **Background:** http://clojure-euler.wikispaces.com/Problem+024
;; With n elements, there are n! permutations; grouping based on the first
;; element in the permutation, there are n permutation groups,
;; each of size (n-1)!. To find the first element of the ith permutation is
;; therefore to find its first element (quotient of (i-1) and n), and then
;; to recur to find the ((i-1) mod n)th permutation of the remaining elements.
;; To avoid having to subtract by 1 each time, the recursive loop uses
;; 0-based indexing, and is initialized with i-1.
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn permute-nth
  "Return permutation pos of the list of digits."
  [s pos] (let [n (count s)
                m (reduce * (range 1 n))
                r (rem pos m)
                e (nth s (quot pos m))]
            (if (= n 1) s
                (cons e (permute-nth (remove #(= % e) s) r)))))

(defn p024
  "**Task:** What is the millionth lexicographic permutation of the digits
  0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?"
  [] (permute-nth (range 10) (dec 1000000)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 25 - 1000-digit Fibonacci number
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn p025
  "**Task:** What is the first term in the Fibonacci sequence
  to contain 1000 digits?"
  ([] (p025 1000))
  ;; Fibonacci terms converge to (n)*Phi=(n+1),
  ;; where Phi is the Golden Ratio (1+sqrt5)/2.
  ([n] (int (Math/ceil
             (/ (+ (dec n) (Math/log10 (Math/sqrt 5)))
                (Math/log10 helper/phi))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 26 - Reciprocal cycles
;;
;; **Description:** A unit fraction contains 1 in the numerator. The decimal
;; representation of the unit fractions with denominators 2 to 10 are given:
;;
;; 1/2	= 	0.5     1/7	= 	0.(142857)
;; 1/3	= 	0.(3)   1/8	= 	0.125
;; 1/4	= 	0.25    1/9	= 	0.(1)
;; 1/5	= 	0.2     1/10	= 	0.1
;; 1/6	= 	0.1(6)
;;
;; Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle.
;; It can be seen that 1/7 has a 6-digit recurring cycle.
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn rec-cycle
  "Find the longest recurring cycle in a fraction."
  [n d] (loop [n n d d acc [] rems #{}]
          (let [div (int (/ n d)) rem (mod n d)]
            (if (zero? rem) ()
                (if (contains? rems rem)
                  (drop-while #(not= % (int (/ (* rem 10) d)))
                              (drop 1 (conj acc div)))
                  (recur (* rem 10) d
                         (conj acc div)
                         (conj rems rem)))))))

(def kvs
  "Recurring cycles of numbers under 1000."
  (map #(vector % (count (rec-cycle 1 %)))
       (take 1000 (iterate dec 1000))))

(defn p026
  "**Task:** Find the value of d < 1000 for which 1/d contains the longest
  recurring cycle in its decimal fraction part."
  [] (first (apply max-key second kvs)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 27 - Quadratic primes
;;
;; **Description:** Euler discovered the remarkable quadratic formula:
;;
;; n² + n + 41
;;
;; It turns out that the formula will produce 40 primes for the consecutive
;; values n = 0 to 39. However, when n = 40, 402 + 40 + 41 = 40(40 + 1) + 41
;; is divisible by 41, and certainly when n = 41, 41² + 41 + 41 is
;; clearly divisible by 41.
;;
;; The incredible formula  n² − 79n + 1601 was discovered,
;; which produces 80 primes for the consecutive values n = 0 to 79.
;; The product of the coefficients, −79 and 1601, is −126479.
;; Considering quadratics of the form:
;;
;; n² + an + b, where |a| < 1000 and |b| < 1000
;;
;; where |n| is the modulus/absolute value of n
;; e.g. |11| = 11 and |−4| = 4
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn quadratic
  "Returns a generator function of the form: n² + an + b."
  [a b] (fn [n] (+ (* (+ n a) n) b)))

(defn consec-primes
  "given a generator function, returns the amount of consecutive
  prime numbers generated"
  [a b] (->>
         (iterate inc 0) ;; for Integers starting from zero
         (map (quadratic a b)) ;; apply the generator of the form: n² + an + b
         (take-while #(and (pos? %) (helper/prime-fast? %))) ;; get all the primes
         (count))) ;; count # of primes

(defn quads
  "All quadratic prime generators between a and b."
  [nums]
  (for [a nums b nums]
    [(consec-primes a b) a b]))

(defn find-max
  "Retrieve the product of the maximum"
  [nums]
  (let [[n a b] (first (sort-by first > (quads nums)))]
    (* a b)))

(defn p027
  "**Task:** Find the product of the coefficients, a and b, for the quadratic
  expression that produces the maximum number of primes for consecutive values
  of n, starting with n = 0."
  ([] (p027 (range -999 1000))) ;; max prime generator between -999 and 999
  ([nums] (find-max nums)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 28 - Number spiral diagonals
;;
;; **Description:**
;; Starting with the number 1 and moving to the right in a clockwise direction
;; a 5 by 5 spiral is formed as follows:
;;
;; 21 22 23 24 25
;; 20  7  8  9 10
;; 19  6  1  2 11
;; 18  5  4  3 12
;; 17 16 15 14 13
;;
;; It can be verified that the sum of the numbers on the diagonals is 101.
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn p028
  "**Task:** What is the sum of the numbers on the diagonals in a 1001 by 1001
  spiral formed in the same way?"
  ([] (p028 1001))
  ([n]
   {:pre [(odd? n)]}      ;; precondition: spirals can only have an odd length
   (if (= n 1) 1          ;; base case: f(1) = 1
       (reduce + (cons
                ;; recursive case: cons it to the recusive call of f(n -2)
                  (p028 (- n 2))
                  (take 4 ;; take four values per 'ring'
                          ;; creating 'ring': from n * n, dec in steps of n- 1
                        (iterate #(- % (dec n)) (* n n))))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 29 - Distinct powers
;;
;; **Description:**
;; Consider all integer combinations of ab for 2 ≤ a ≤ 5 and 2 ≤ b ≤ 5:
;;
;; 2^2=4,  2^3=8,   2^4=16,  2^5=32
;; 3^2=9,  3^3=27,  3^4=81,  3^5=243
;; 4^2=16, 4^3=64,  4^4=256, 4^5=1024
;; 5^2=25, 5^3=125, 5^4=625, 5^5=3125
;; If they are then placed in numerical order, with any repeats removed,
;; we get the following sequence of 15 distinct terms:
;;
;; 4, 8, 9, 16, 25, 27, 32, 64, 81, 125, 243, 256, 625, 1024, 3125
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn p029
  "**Task:** How many distinct terms are in the sequence generated by ab
  for 2 ≤ a ≤ 100 and 2 ≤ b ≤ 100?"
  ([] (p029 100))
  ([n]
   (->>
    ;; typical list comprehension problem
    (for [a (range 2 (inc n))
          ;; for 2 to n
          b (range 2 (inc n))]
      ;; return a^b
      (Math/pow a b))
    ;; distinct elements
    (set)
    ;; count them
    (count))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 30 - Digit fifth powers
;;
;; **Description:** Surprisingly there are only three numbers that can be
;; written as the sum of fourth powers of their digits:
;;
;; 1634 = 1^4 + 6^4 + 3^4 + 4^4
;; 8208 = 8^4 + 2^4 + 0^4 + 8^4
;; 9474 = 9^4 + 4^4 + 7^4 + 4^4
;;
;; As 1 = 1^4 is not a sum it is not included. The sum of these numbers
;; is 1634 + 8208 + 9474 = 19316.
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn p030
  "**Task:** Find the sum of all the numbers that can be written as
  the sum of fifth powers of their digits."
  ([] (p030 5))
  ; Pre-calculate digit powers [0^exp, 1^exp, ..., 9^exp]
  ([exp] (let [digit-powers (long-array (map #(long (Math/pow % exp)) (range 10)))
               ; Calculate upper bound: 9^exp * (digits in number)
               max-num (loop [n 9 sum (* 9 (long (Math/pow 9 exp)))]
                         (let [next-sum (+ sum (long (Math/pow 9 exp)))]
                           (if (> next-sum (Math/pow 10 (inc n)))
                             (recur (inc n) next-sum)
                             (long sum))))]
           
           (loop [n 2 total 0]
             (if (> n max-num) total 
                 (let [sum (loop [num n s 0] 
                             (if (zero? num) s
                                 (recur (quot num 10) (+ s (aget digit-powers (rem num 10))))))]
                   (recur (inc n) (if (= n sum) (+ total sum) total))))))))
