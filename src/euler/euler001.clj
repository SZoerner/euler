(ns euler.euler001
  (:use euler.helper))

;; # Problem 1 - Multiples of 3 and 5
;;
;; **Description:** 
;; If we list all the natural numbers below 10 
;; that are multiples of 3 or 5, we get 3, 5, 6 and 9. 
;; The sum of these multiples is 23.
;;
;; **Task**: 
;; Find the sum of all the multiples of 3 or 5 below 1000.

(defn p001
  "*Int, (Int) -> Int* 
  Sums up the multiples of all integers below limit."
  ([] (p001 1000 3 5))
  ([limit & divisors]
    ;; for the range 0 to (limit - 1)
   (->> (range limit)
         ;; filter the multiples (at least one divisor)
        (filter (reduce factor-any divisors))
         ;; and sum up the resulting list
        (reduce +))))


;; # Problem 2 - Even Fibonacci numbers
;; 
;; **Description:** 
;; Each new term in the Fibonacci sequence is generated 
;; by adding the previous two terms. By starting with 1 and 2, the first 10 terms will be:
;; (1), 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
;; 
;; **Task:** 
;; By considering the terms in the Fibonacci sequence whose values do not exceed four million, 
;; find the sum of the even-valued terms.
;;
;; **Background**
;; A Fibonacci number (exept 0 and 1) is recursively defined as the sum of its two predecessing Fibonacci numbers:  
;; - base case: ``fib(n <= 1) = n``  
;; - rec case: ``fib (n > 1) = fib(n - 1) + fib(n - 2)``

(def fibs
  "*(Int)*  
  Lazy sequence of all Fibonacci numbers."
  (lazy-cat [0 1] (map + (rest fibs) fibs)))

(defn p002
  "*Int -> Int*  
  Retrieve the sum of all even Fibonacci numbers up to n."
  ([] (p002 (* 4 1000 1000)))
  ([n]
   (->> fibs
        (take-while #(< % n))
        (filter even?)
        (reduce +))))


;; # Problem 3 - Largest prime factor
;; 
;; **Description:** 
;; The prime factors of 13195 are 5, 7, 13 and 29.
;; 
;; **Task:**
;; What is the largest prime factor of the number 600851475143 ?

(defn max-prime 
  "Given a number n and a list of prime numbers ps, returns the largest prime factor of n."
  [n ps]
  ;; local variable - head of prime list
  (let [div (first ps)]
    (cond
      ;; termination - found max prime
      (= n div) div
      (factor? n div) (max-prime (/ n div) ps)
      ;; list eater
      :else (max-prime n (rest ps)))))

(defn p003 [n]
  (max-prime n primes))

;; calculation
;; (p003-lazy 600851475143)


;; # Problem 4 - Largest palindrome product
;; 
;; **Description:** 
;; A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
;; Find the largest palindrome made from the product of two 3-digit numbers.

(defn p004 [n]
  ;; get the largest result of
  (reduce max
          (for [num1 (range n 0 -1)
                ;; all numbers from 100 to 1000
                num2 (range n 0 -1)
                ;; compute the product of the two
                :let [pal (* num1 num2)]
                ;; and filter those who are palindromes
                :when (= (str pal)
                         ;; by converting to string and compare to the reversed string
                         (clojure.string/reverse (str pal)))]
            pal)))

;; calculation
;(p004 1000)


;; # Problem 5 - Smallest multiple
;; 
;; **Description:** 
;; 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
;; 
;; **Task:**
;; What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?

;; reference for computing the least common multiple
;; http://en.wikipedia.org/wiki/Least_common_multiple

(defn p005 [n]
  (->>
   (range (inc n))
   (map #(frequencies (get-primes %)))
   (apply merge-with max)
   (map #(Math/pow (first %) (second %)))
   (reduce *)
   (int)))

;; calculation
;; (p005 20)


;; #Problem 6 - Sum square difference
;; 
;; **Description:** 
;; The sum of the squares of the first ten natural numbers is 1^2 + 2^2 + ... + 10^2 = 385
;; The square of the sum of the first ten natural numbers is (1 + 2 + ... + 10)^2 = 55^2 = 3025
;; Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.
;; 
;; **Task:**
;; Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.

(defn p006 [n]
  (int (- (Math/pow (reduce + (range (inc n))) 2)
          (reduce + (map #(Math/pow % 2) (range (inc n)))))))

(defn p006 [n]
  (let [nums (range (inc n))]
    (int (- (Math/pow (reduce + nums) 2)
            (reduce + (map #(Math/pow % 2) nums))))))

;; calculation
;; (p006 100)

;; # Problem 7 - 10001st prime
;; 
;; **Description:** 
;; By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
;; 
;; **Task:**
;; What is the 10 001st prime number?

;; type: number => number
(defn p007 [n]
  (->>
    ;; for all positive integers
   (range 1 Integer/MAX_VALUE)
    ;; shortcut to remove even numbers
   (take-nth 2)
    ;; only prime numbers left
   (filter prime?)
    ;; realize n items
   (take (dec n))
    ;; pick the nth element
   last))

;; calculation
;; (p007 10001)


;; # Problem 8 - Largest product in a series
;; 
;; **Task:**
;; Find the greatest product of five consecutive digits in the 1000-digit number.

(defn p008
  ([] (p008 5 (bigdec (slurp "resources/p008_digit.txt"))))
  ([n series]
   (->>
      ;; hack to retrieve individual digits
    (str series)
      ;; Int => List[Int]
    (map #(Integer/parseInt (str %)))
      ;; partition into lists of 5
    (partition n 1)
      ;; calculate the product
    (map #(reduce * %))
      ;; get the highest product
    (reduce max))))


;; # Problem 9 - Special Pythagorean triplet
;; 
;; **Description:** 
;; A Pythagorean triplet is a set of three natural numbers, a < b < c, for which a^2 + b^2 = c^2
;; For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
;; 
;; **Task:**
;; There exists exactly one Pythagorean triplet for which a + b + c = 1000. Find the product abc.

(defn p009
  ([] (p009 1000))
  ([n]
   (first (for [a (range n)
                b (range (- n a))
                :let [c (- n (+ a b))]
                :when (and (< a b c)
                           (= (+ (Math/pow a 2) (Math/pow b 2))
                              (Math/pow c 2)))]
            (* a b c)))))


;; # Problem 10 - Summation of primes
;; 
;; **Description:** 
;; The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
;; 
;; **Task:**
;; Find the sum of all the primes below two million.

(defn p010 [n]
  (->>
   primes
   (take-while #(< % n))
   (reduce +)))

;; calculation
;; (p010 2000000)