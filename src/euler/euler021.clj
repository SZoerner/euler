(ns euler.euler021)

; Problem 21 - Amicable numbers
; ------------------------------
; Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
; If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.
; For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284.
; The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
;
; Evaluate the sum of all the amicable numbers under 10000.

(let
  [amicable
   (fn [n]
     (reduce + (filter #(= 0 (mod n %)) (range 1 (+ 1 (/ n 2))))))

   amicable?
   (fn [a]
     (let [b (amicable a)]
       (if (and (= (amicable b) a) (not (= a b)))
         [a b] ())))]

  (reduce + (distinct (flatten (map amicable? (range 1 10001))))))


; Problem 22 - Names scores
; --------------------------
; Using 'http://projecteuler.net/project/names.txt', a 46K text file containing over five-thousand first names, begin by sorting it into alphabetical order.
; Then working out the alphabetical value for each name, multiply this value by its alphabetical position in the list to obtain a name score.
; For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list.
; So, COLIN would obtain a score of 938 × 53 = 49714.
; What is the total of all the name scores in the file?

(let
  [input  (sort (re-seq #"\w+" (slurp "http://projecteuler.net/project/names.txt")))
   ; sum of the numerical representation of each character
   get-value (fn [name]
               (->> name
                    (map int)
                    (map #(partial (- % 64)))
                    (reduce +)))
   ; get the position
   get-index (fn [name]
               (+ 1 (.indexOf input name)))]
  ; compute the product of value and position
  (reduce + (map #(* (get-index %) (get-value %)) input)))


; Problem 23 - Non-abundant sums
; -------------------------------
; A perfect number is a number for which the sum of its proper divisors is exactly equal to the number.
; For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.
; A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum exceeds n.
;
; As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as the sum of two abundant numbers is 24.
; By mathematical analysis, it can be shown that all integers greater than 28123 can be written as the sum of two abundant numbers.
; However, this upper limit cannot be reduced any further by analysis even though it is known that the greatest number
; that cannot be expressed as the sum of two abundant numbers is less than this limit.
;
; Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.

(let
  ; Number -> List[Numbers] - divisors of n (excluding n)
  [divisors
   (fn [n]
     (filter #(= 0 (mod n %)) (range 1 n)))

   ; Number -> Boolean - is n an abundant number?
   abundant?
   (fn [n]
     (< n (reduce + (divisors n))))

   ; Number, Set[Number] -> Boolean - are there two elements in abundants which sum is i?
   sum-of-abundants?
   (fn [i abundants]
     (some (fn [a] (abundants (- i a))) abundants))

   ; List[Numbers] - input numbers from 1 up to 28124
   input (range 1 (+ 1 28123))

   ; Set[Numbers] - all abundant numbers up to input
   abundants (into (sorted-set) (filter abundant? input))]

  ; calculation - sum up all 'non-abundant-sums' up to input
  (reduce + (filter #(not (sum-of-abundants? % abundants)) input)))


; Problem 24 - Lexicographic permutations
; ----------------------------------------
; A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4.
; If all of the permutations are listed numerically or alphabetically, we call it lexicographic order.
; The lexicographic permutations of 0, 1 and 2 are: 012   021   102   120   201   210
; What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?

; http://clojure-euler.wikispaces.com/Problem+024
; With n elements, there are n! permutations; grouping based on the first element in the permutation, there are n permutation groups,
; each of size (n-1)! . To find the first element of the ith permutation is therefore to find its first element (quotient of (i-1) and n),
; and then to recur to find the ((i-1) mod n)th permutation of the remaining elements.
; To avoid having to subtract by 1 each time, the recursive loop uses 0-based indexing, and is initialized with i-1

(defn permute-nth [s pos]
  (let [n (count s)
        m (reduce * (range 1 n))
        r (rem pos m)
        e (nth s (quot pos m))]
    (if (= n 1) s
      (cons e (permute-nth (remove #(= % e) s) r)))))

(print (str (permute-nth (range 10) (dec 1000000))))


; Problem 25 - 1000-digit Fibonacci number
; -----------------------------------------
; What is the first term in the Fibonacci sequence to contain 1000 digits?

; compute the nth element in the Fibonacci sequence
(defn fib                           ; overloaded function
  ([n] (if (= n 0) 0                ; with one parameter
         (fib n (BigInteger. "0")   ; using Java's BigInteger
              (BigInteger. "1"))))  ; cached version using an accumulator
  ([n p0 p1]                        ; with three parameters
   (if (= n 1) p1
     (fib (- n 1) p1 (+ p0 p1)))))

; count the number of digits
(defn digits [n]
  (count (str n))) ; by converting to string

(first                           ; get the first element
 (drop-while                     ; for which no longer holds
  (comp #(> 1000 %) digits fib)  ; that the count of digits
  (range 1 5000)))               ; of the fib seq is below 1000.


; Problem 26 - Reciprocal cycles
; -------------------------------
; A unit fraction contains 1 in the numerator.
; The decimal representation of the unit fractions with denominators 2 to 10 are given:
;
; 1/2	= 	0.5     1/7	= 	0.(142857)
; 1/3	= 	0.(3)   1/8	= 	0.125
; 1/4	= 	0.25    1/9	= 	0.(1)
; 1/5	= 	0.2     1/10	= 	0.1
; 1/6	= 	0.1(6)
;
; Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle.
; Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.

; find the longest recurring cycle in a fraction
(defn rec-cycle [n d]
  (loop [n n d d acc [] rems #{}]
    (let [div (int (/ n d)) rem (mod n d)]
      (if (= rem 0) ()
        (if (contains? rems rem)
          (drop-while #(not (= % (int (/ (* rem 10 ) d))))
                      (drop 1(conj acc div)))
          (recur (* rem 10) d
                 (conj acc div)
                 (conj rems rem)))))))

       (apply max-key second (map #(conj [] % (count (rec-cycle 1 %)))
            (take 1000 (iterate dec 1000))))
