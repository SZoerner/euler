(ns euler.euler041
  (:require [euler.helper :as helper]
            [clojure.set :as s]
            [clojure.string :as str]))

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
  ([] (p041 9))
  ([n] (let [digit-sum (fn [n] (reduce + (range 1 (inc n))))]
         (->> (range n 0 -1) ;; Work backwards from 9 to 1 to start with the largest.
              (remove #(zero? (mod (digit-sum %) 3))) ;; Skip numbers whose digits sum to multiple of 3 (not prime)
              (mapcat #(helper/permutations (range % 0 -1))) ;; Get permutations in descending order
              (map #(read-string (apply str %)))
              (filter helper/prime-fast?)
              first)))) ;; Return first match since we're going largest to smallest.


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

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 43 - Sub-string divisibility
;;
;; The number, 1406357289, is a 0 to 9 pandigital number because it is made up
;; of each of the digits 0 to 9 in some order, but it also has a rather
;; interesting sub-string divisibility property. Let d1 be the 1st digit,
;; d2 be the 2nd digit, and so on. In this way, we note the following:
;;
;; d2d3d4=406 is divisible by 2
;; d3d4d5=063 is divisible by 3
;; d4d5d6=635 is divisible by 5
;; d5d6d7=357 is divisible by 7
;; d6d7d8=572 is divisible by 11
;; d7d8d9=728 is divisible by 13
;; d8d9d10=289 is divisible by 17
;;
;; Find the sum of all 0 to 9 pandigital numbers with this property.
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Get all three-digit numbers that are multiples of p
(defn divisibles [p]
  (->> (iterate (partial + p) p)
       (take-while #(< % 1000))
       (map #(format "%03d" %))))

; Unify nested maps with sets as leaves
(defn deep-union [& maps]
  (if (every? map? maps)
    (apply merge-with deep-union maps)
    (apply s/union maps)))

; Build a tree data structure to fast retrieve the three-digit number divisible by p.
(defn lookup-tree [p]
  (->> (divisibles p)
       (map (fn [[x1 x2 x3]] {x3 {x2 #{x1}}}))
       (apply deep-union)
       (hash-map p)))

(defn p043 []
  (let [tree (into {} (map lookup-tree [2 3 5 7 11 13 17]))
        pans (for [[d8 d9 d10] (divisibles 17)
                   d7 (get-in tree [13 d9 d8])
                   d6 (get-in tree [11 d8 d7])
                   d5 (get-in tree [7 d7 d6])
                   d4 (get-in tree [5 d6 d5]) 
                   d3 (get-in tree [3 d5 d4])
                   d2 (get-in tree [2 d4 d3])
                   :when (and (s/superset? #{\0 \5} #{d6})
                              (s/superset? #{\0 \2 \4 \6 \8} #{d4})
                              (zero? (mod (Long/parseLong (str d3 d4 d5)) 3)))
                   :let [res (str d2 d3 d4 d5 d6 d7 d8 d9 d10)
                         fst (reduce (fn [s c] (str/replace s (str c) "")) "0123456789" res)]
                   :when (= 1 (count fst))]
               (Long/parseLong (str (first fst) res)))]
    (reduce + pans)))

;; -- Simpler, but slower variant.
(defn- substrings
  [ds] (map #(Integer/parseInt (apply str %)) (partition 3 1 ds)))

(defn- prime-divisible? [xs]
  (->> (take 7 helper/primes)
       (map helper/factor? (rest xs))
       (every? true?)))

(defn- p043-slow []
  (->> (range 10) ; For all digits.
       helper/permutations ; Generate all permutations.
       (filter #(prime-divisible? (substrings %))) ; Filter permutations by prime divisibility.
       (map #(Long/parseLong (apply str %))) ; Convert to long. 
       (reduce +))) ; Sum the results.


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 44 - Pentagon numbers
;;
;; Pentagonal numbers are generated by the formula, Pn=n(3n−1)/2. The first ten
;; pentagonal numbers are: 1, 5, 12, 22, 35, 51, 70, 92, 117, 145, ...
;;
;; It can be seen that P4 + P7 = 22 + 70 = 92 = P8. However, their difference,
;; 70 − 22 = 48, is not pentagonal. Find the pair of pentagonal numbers, Pj and
;; Pk, for which their sum and difference are pentagonal and D = |Pk − Pj| is
;; minimised; what is the value of D?
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Pn=n(3n−1)/2 = (3n^2-n)/2
(defn nth-pentagonal [n]
  (/ (* n (dec (* 3 n))) 2))

;; inverse the quadratic formula: https://www.educative.io/answers/project-euler-44-pentagonal-numbers
(defn pentagonal? [n]
  (zero? (mod (+ 1 (Math/sqrt (+ 1 (* 24 n)))) 6)))

(defn p044 []
  (first (for [k (range)
               j (range 1 k)
               :let [x (nth-pentagonal k)
                     y (nth-pentagonal j)]
               :when (and (< y x) (pentagonal? (+ x y)) (pentagonal? (- x y)))]
           (- x y))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 45 - Triangular, pentagonal, and hexagonal
;;
;; Triangle, pentagonal, and hexagonal numbers are generated by the following 
;; formulae:
;;
;; Triangle	 	  Tn=n(n+1)/2	 	1, 3, 6, 10, 15, ...
;; Pentagonal	 	Pn=n(3n−1)/2	 	1, 5, 12, 22, 35, ...
;; Hexagonal	 	Hn=n(2n−1)	 	1, 6, 15, 28, 45, ...
;;
;; It can be verified that T285 = P165 = H143 = 40755.
;; Find the next triangle number that is also pentagonal and hexagonal.
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn triangle? [n]
  (zero? (mod (- (Math/sqrt (+ 1 (* 8 n))) 1) 2)))

;; Hn=n(2n−1) = 2n^2 - n
(defn hexagonal [n]
  (* n (- (* 2 n) 1)))

(defn p045 []
  (second (for [n (drop 2 (range))
                :let [hex (hexagonal n)]
                :when (and (pentagonal? hex) (triangle? hex))]
            hex)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 46 - Goldbach's other conjecture
;;
;; It was proposed by Christian Goldbach that every odd composite number can
;; be written as the sum of a prime and twice a square.
;;
;; 9 = 7 + 2×1^2
;; 15 = 7 + 2×2^2
;; 21 = 3 + 2×3^2
;; 25 = 7 + 2×3^2
;; 27 = 19 + 2×2^2
;; 33 = 31 + 2×1^2
;;
;; It turns out that the conjecture was false.
;;
;; What is the smallest odd composite that cannot be written as the sum of a 
;; prime and twice a square?
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; composite numbers are those that are not prime and not 1.
(def odd-composites
  (filter #(and (odd? %) (not (helper/prime-fast? %))) (drop 2 (range))))

(defn conjecture? [n]
  (let [ps (take-while #(<= % n) helper/primes)
        is-int? (fn [n] (== (int n) n))
        conjs (filter #(is-int? (Math/sqrt (/ (- n %) 2))) ps)]
    (first conjs)))

(defn p046 []
  (first (filter (complement conjecture?) odd-composites)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 47 - Distinct primes factors
;;
;; The first two consecutive numbers to have two distinct prime factors are:
;;
;; 14 = 2 × 7
;; 15 = 3 × 5
;;
;; The first three consecutive numbers to have three distinct prime factors are:
;;
;; 644 = 2² × 7 × 23
;; 645 = 3 × 5 × 43
;; 646 = 2 × 17 × 19.
;;
;; Find the first four consecutive integers to have four distinct prime factors 
;; each. What is the first of these numbers?
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn distinct-factors [n]
  (if (= 0 n) 0
      (count (set (helper/prime-factors-of n)))))

(defn has-primes? [x ns]
  (every? (partial = x) (map distinct-factors ns)))

(defn p047 []
  (->> (range)
       (partition 4 1)
       (filter (partial has-primes? 4))
       first))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 48 - Self powers
;;
;; The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.
;;
;; Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn p048 []
  (->> (range 1 1001)
       (map #(helper/exp-by-square % %))
       (reduce +)
       helper/digits
       reverse
       (take 10)
       reverse
       (apply str)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 49 - Prime permutations
;;
;; The arithmetic sequence, 1487, 4817, 8147, in which each of the terms 
;; increases by 3330, is unusual in two ways: (i) each of the three terms 
;; are prime, and, (ii) each of the 4-digit numbers are permutations of 
;; one another.
;;
;; There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, 
;; exhibiting this property, but there is one other 4-digit increasing sequence.
;;
;; What 12-digit number do you form by concatenating the three terms in this sequence?
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn combinations [ns]
  (for [x ns y ns
        :when (< x y)]
    [x y]))

(defn map-increase [sequence]
  (reduce (fn [a b] (when (= (last a) (first b)) (concat a [(second b)]))) sequence))

(defn distances [ns]
  (->> ns
       combinations
       (group-by #(abs (- (second %) (first %))))
       (reduce (fn [m [k v]] (assoc m k (map-increase v))) {})))

(def get-increments
  (->> helper/primes
       (drop-while #(< % 1000))
       (take-while #(< % 10000))
       (group-by #(vec (sort (helper/digits %))))
       (reduce (fn [m [k v]] (assoc m k (distances v))) {})
       (mapcat (fn [[_ v]] (filter #(= 3 (count (second %))) v)))))

(defn p049 []
  (->> get-increments
       first
       second
       (apply str)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; # Problem 50 - Consecutive prime sum
;;
;; The prime 41, can be written as the sum of six consecutive primes:
;;
;; 41 = 2 + 3 + 5 + 7 + 11 + 13
;; This is the longest sum of consecutive primes that adds to a prime 
;; below one-hundred. The longest sum of consecutive primes below one-thousand 
;; that adds to a prime, contains 21 terms, and is equal to 953.
;;
;; Which prime, below one-million, can be written as the sum of the most 
;; consecutive primes?
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Make a list of primes up to a million with a sieve, then:
;; 1) add up primes from the beginning till the sum exceeded 1mil.
;; 2) drop primes from the end until the sum was a prime
;; 3) store the result if it's the longest run so far
;; 4) drop one prime from the start of the list, and repeat

(defn conseq-primes [below]
  (loop [primes (take-while #(< % below) helper/primes)
         longest {:seq [] :sum 0 :len 0}]
    (if (empty? primes) (:sum longest)
        (let [running-sum (reductions + primes)
              exceeds (into [] (take-while #(< % below) running-sum))
              val (first (filter helper/prime-fast? (rseq exceeds)))
              idx (.indexOf exceeds val)
              longest-here {:seq (take idx primes) :sum val :len idx}]
          (recur (rest primes) (max-key #(:len %) longest longest-here))))))

(defn p050 [] (conseq-primes 1e6))
