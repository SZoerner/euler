(ns euler.helper)

(defn factor?
  "**factor? :: Int, Int -> Bool**:
  Predicate that tests whether the divisor evenly divides the dividend."
  [dividend divisor] (zero? (mod dividend divisor)))

(defn factor-any
  "**factor-any :: [Int] -> (Int -> Bool)**:
  Returns a predicate that tests whether its argument
  can be evenly divided by any of the divisors."
  [& divisors]
  (fn [argument]
    (boolean (some #(factor? argument %) divisors))))

(defn factors
  "**factors :: Int -> [Int]**
  Returns all numbers that evenly divide n."
  ([n] (factors n 1))
  ([n start]
   (lazy-seq (let [lower (filter #(factor? n %)
                                  ;; iterate up to sqrt(n)
                                 (range start (inc (Math/sqrt n))))
                    ;; add the coresponding divisor pairs
                   upper (map #(/ n %) lower)]
               (set (concat lower upper))))))

;; TODO replace with facts seq
(defn factorial
  "*Int -> [Int]*
  Multiplies all natural numbers from 1 to n+1."
  [n] (reduce * (range 1N (inc n))))

(def factorials
  "**facts :: [Int]**
  Lazy, infinite sequence of all factorial numbers."
  (lazy-cat [1] (map * factorials (iterate inc 2))))

(defn lattice-paths [n]
  ;; divide the factorial of 2n
  (long (/ (factorial (* 2 n))
           ;; by (fac(n))^2
           (Math/pow (factorial n) 2))))

(defn triangle
  "**triangle :: Int -> Int**
  Computes the nth triangle number.
  A triangle number is generated by adding the natural numbers up to n.
  Shortcut using Gauss technique."
  [n] (/ (* n (inc n)) 2))

(def fibs
  "**fibs :: [Int]**
  Lazy sequence of all Fibonacci numbers. Using BigIntegers."
  (lazy-cat [0N 1N] (map + (rest fibs) fibs)))

(defn next-collatz
  "computes the next number of the collatz sequence."
  [n] (if (even? n) (bit-shift-right n 1) (inc (* 3 n))))

(defn collatz
  "**collatz :: [Int]**
  Lazy seq of Collatz sequence of n."
  [n] (cons n
        (if (= n 1) '()
            (collatz (next-collatz n)))))

(defn memo-collatz [c n] ;; TODO not sure this works efficiently..
  (if-let [entry (@c n)] entry ;; already present - return the count
          ((swap! c assoc n  ;; new entry - store in cache
                  (inc (memo-collatz c (next-collatz n))))
           n)))

(defn parse-grid
  "**parse-grid :: String -> [[Int]]**
  Converts a string into a dimension x dimension vector of integers."
  [grid-str dimension] (->> (.split grid-str "\\s+")
                            (map #(Integer. %))
                            (partition dimension)
                            (map vec)
                            (vec)))

(defn product
  "Inspired by Chouser"
  [size len grid]
  ;; only works for quadratic matrices
  {:pre [(= size (count grid) (count (first grid)))]}
  ;; get the highest value
  (reduce max
          (for [sx (range 0 size)
                sy (range 0 size)
                ;;          \/    \     ->     /
                delta-yx [[1 0] [1 1] [0 1] [-1 1]]]
            ;; compute the product
            (reduce *
                    ;; partition into sequences of four
                    (for [yx (take len (iterate #(map + delta-yx %) [sy sx]))
                          :while (every? #(< -1 % size) yx)]
                      (reduce nth grid yx))))))

(def primes
  "**primes :: [Int]**
  Lazy stream of prime numbers, generated by the
  sieve of Eratosthenes algorithm. Internally stores in a hastable the next
  composite number corresponding to the prime number.
  Taken from http://voidmainargs.blogspot.de/2010/12/
  lazy-sieve-eratosthenes-in-scala-and.html"
  (letfn [(next-comp [x step sieve]
            (if (sieve x)
            ;; the composite number has already been found previously
              (recur (+ x step) step sieve)
            ;; brand new composite number
              (assoc sieve x step)))
          (next-prime [x sieve]
            (let [step (sieve x)]
              (if (sieve x)
            ;; found a composite
                (recur (+ x 2) (next-comp (+ x step) step (dissoc sieve x)))
            ;; found a prime
                (cons x
                      (lazy-seq
                       (next-prime (+ x 2) (assoc sieve (* x x) (* x 2))))))))]
    (cons 2 (lazy-seq (next-prime 3 {})))))

(defn prime?
  "**prime? :: Int -> Bool**
  Checks whether a given number n is a prime number."
  [n] (and (< 1 n)
       (not-any? #(factor? n %)
                 (take-while #(<= (* % %) n) primes))))

;; iterative approach
(defn get-primes
  ;; entry point - start with the list of primes and [] as accumulator
  ([n] (get-primes n primes []))
  ([n ps acc]
   (let [[f & r] ps]
     (cond
       ;; no more factors - stop iteration
       (< n f) acc
       ;; if factor - add to set of prime factors
       (factor? n f) (get-primes (/ n f) ps (conj acc f))
       ;; no match - next iteration
       :else (get-primes n r acc)))))

(defn max-prime
  "**max-prime :: Int, [Int] -> Int**
  Given a number n and a list of prime numbers ps,
  returns the largest prime factor of n."
  [n ps]
  (let [div (first ps)] ;; local variable - head of prime list
    (cond
      ;; termination - found max prime
      (= n div) div
      (factor? n div) (max-prime (/ n div) ps)
      ;; list eater
      :else (max-prime n (rest ps)))))

(defn prime-factors
  "** prime-factors :: Int -> [Int]**
Given a number, returns the list of prime factors of n.
Example: (prime-factors 12) => (2 2 3)"
  ([n] (prime-factors n [] primes))
  ([number factors ps]         ;; TODO use destructuring
   (cond
     (= 1 number) factors
     (factor? number (first ps)) (prime-factors (/ number (first ps))
                                                (conj factors (first ps)) ps)
     :else (prime-factors number factors (rest ps)))))

(defn count-divisors
  "**count-divisors :: Int -> Int**
Calculates the number of divisors of n (including 1 and n itself)."
  [n] (->> (prime-factors n)
           (partition-by identity)
           (map count)
           (map inc)
           (reduce *)))

(defn palindrome?
  "**palindrome? :: Int -> Bool**
  Checks whether the given number n is a palindrome.
  Uses reversed string comparison"
  [n] (= (str n) (clojure.string/reverse (str n))))

(defn least-common-multiple
  "**least-common-multiple :: [Int] -> Int**
  Computes the smallest number divisible by all of the given input numbers."
  [input]
  (->> input
       (map #(frequencies (get-primes %)))
       (apply merge-with max)
       (map #(Math/pow (first %) (second %)))
       (reduce *)
       (int)))

(defn sum-of-factors
  "The sum of all proper divisors, excluding  n."
  [n] (reduce + 1 (factors n 2))) ; include 1, but ignore n

(defn amicable? [a]
  (let [b (sum-of-factors a)]
    (if (and (not= a b) (= a (sum-of-factors b)))
      [a b] ())))

(defn abundant?
  "**abundant? :: Int -> Bool**
  Checks whether the sum of the factors of n (excluding n) is greater than n."
  [n] (< (* 2 n) (reduce + (factors n))))

(def abundants (into (sorted-set) (filter abundant? (range 12 28124))))

(defn abundant-sum?
  "**abundant-sum? :: Int -> Boolean**
  Can n be expressed as the sum of two abundant numbers?"
  [n] (boolean (some #(abundants (- n %)) (take-while #(< % n) abundants))))

(defn truncate
  "**truncate :: Int, Int -> Int**
  Truncates the given number up to the first n digits."
  [number digits]
  (->> digits
       (inc)
       (- (count (str number)))
       (Math/pow 10)
       (/ number)
       (Math/floor)))

(def phi
  "The Golden Ratio - (1+sqrt5)/2."
  (/ (inc (Math/sqrt 5)) 2))

(defn digits
  "**digits :: Int -> [Int]**
  Converts a number n into a list of its digits."
  [n] (map #(Character/digit % 10) (str n)))

(defn narcissistic?
  "A number written as the sum of its nth powers.
  Exp.: 1634 = 1^4 + 6^4 + 3^4 + 4^4."
  [n exp]
  (->> (digits n)
       (map #(Math/pow % exp))
       (reduce +)
       (== n)))

(def singles
  ["one" "two" "three" "four" "five" "six" "seven" "eight" "nine" "ten"
   "eleven" "twelve" "thirteen" "fourteen" "fifteen" "sixteen" "seventeen"
   "eighteen" "nineteen"])

(def tens ["twenty" "thirty" "forty" "fifty"
           "sixty" "seventy" "eighty" "ninety"])

(defn to-words
  "Converts a number (up to 1000) into its string representation,
  omitting spaces. Example: (to-words 115) => 'onehundredandfifteen'."
  [n]
  (cond
    (< n 20) (nth singles (dec n))
    (< n 100) (if (factor? n 10)
                (nth tens (/ (- n 20) 10))
                (str (nth tens (/ (- n 20) 10)) (nth singles (dec (mod n 10)))))
    (< n 1000) (if (factor? n 100)
                 (str (to-words (/ n 100)) "hundred")
                 (str (to-words (/ n 100)) "hundredand" (to-words (rem n 100))))
    (= n 1000) "onethousand"))

(defn combinations [amount [car & cdr :as coins]]
  (cond
    (zero? amount) 1
    (or (neg? amount) (empty? coins)) 0
    :else (+ (combinations amount cdr)
             (combinations (- amount car) coins))))
