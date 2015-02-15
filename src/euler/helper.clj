(ns euler.helper)

(defn factor?
  "*Int, Int -> Bool*  
  Predicate that tests whether the divisor evenly divides the dividend."
  [dividend divisor]
  (zero? (mod dividend divisor)))

(defn factor-any
  "*[Int] -> Int -> Bool*  
  Returns a predicate that tests whether its argument 
  can be evenly divided by any of the divisors."
  [& divisors]
  (fn [argument]
    (boolean (some #(factor? argument %) divisors))))

;; returns a list of factors
(defn factors 
  "*Int -> [Int]*"
  [n]
  (lazy-seq (let [lower (filter #(factor? n %)
                      ;; calculate up to sqrt(n)
                                (range 1 (inc (Math/sqrt n))))
        ;; add the coresponding pairs by division
                  upper (map #(/ n %) lower)]
              (set (concat lower upper)))))

(def fibs
  "*(Int)*  
  Lazy sequence of all Fibonacci numbers. Using BigIntegers."
  (lazy-cat [0N 1N] (map + (rest fibs) fibs)))

(def primes
  "*[Int]*  
  Lazy stream of prime numbers, generated by the sieve of Eratosthenes algorithm.  
  Internally stores in a hastable the next composite number corresponding to the prime number.  
  Taken from http://voidmainargs.blogspot.de/2010/12/lazy-sieve-eratosthenes-in-scala-and.html"
  (letfn [(next-composite [x step sieve]
            (if (sieve x)
            ;; the composite number has already been found previously
              (recur (+ x step) step sieve)
            ;; brand new composite number
              (assoc sieve x step)))
          (next-prime [x sieve] 
            (let [step (sieve x)]         
              (if (sieve x)
            ;; found a composite
                (recur (+ x 2) (next-composite (+ x step) step (dissoc sieve x)))
            ;; found a prime
                (cons x 
                      (lazy-seq 
                       (next-prime (+ x 2) (assoc sieve (* x x) (* x 2))))))))]
    (cons 2 (lazy-seq (next-prime 3 {})))))


(defn prime?
  "*Int -> Bool*  
	Checks whether a given number n is a prime number."
  [n]
  (= (count (factors n)) 2))

;; iterative approach
(defn get-primes
  ;; entry point - start with the list of primes and the empty vector as accumulator
  ([n] (get-primes n primes [])) 
  ([n ps acc]
   (cond
      ;; no more factors - stop iteration
     (< n (first ps)) acc
      ;; if factor - add to set of prime factors
     (factor? n (first ps)) (get-primes (/ n (first ps)) ps (conj acc (first ps)))
      ;; no match - next iteration
     :else (get-primes n (rest ps) acc))))

(defn max-prime 
  "*Int, [Int] -> Int*  
  Given a number n and a list of prime numbers ps, returns the largest prime factor of n."
  [n ps]
  ;; local variable - head of prime list
  (let [div (first ps)]
    (cond
      ;; termination - found max prime
      (= n div) div
      (factor? n div) (max-prime (/ n div) ps)
      ;; list eater
      :else (max-prime n (rest ps)))))

(defn prime-factors 
  "*Int -> [Int]*  
Given a number, returns the list of prime factors of n.  
Example: (prime-factors 12) => (2 2 3)"
  ([n] (prime-factors n [] primes))
  ([num factors ps]         ;; TODO use destructuring
   (cond 
     (= 1 num) factors
     (factor? num (first ps)) (prime-factors (/ num (first ps)) (conj factors (first ps)) ps)
     :else (prime-factors num factors (rest ps)))))

(defn palindrome? 
  "*Int -> Bool*  
	Checks whether the given number n is a palindrome. 
	Uses reversed string comparison"
  [n] (= (str n) (clojure.string/reverse (str n))))


(defn least-common-multiple
  "*[Int] -> Int*  
	Computes the smallest number divisible by all of the given input numbers."
  [input] 
  (->> input
       (map #(frequencies (get-primes %)))
       (apply merge-with max)
       (map #(Math/pow (first %) (second %)))
       (reduce *)
       (int)))

(defn abundant?
  "*Int -> Bool*  
  Checks whether the sum of the factors of n is greater than n."
  [n] (< n (reduce + (factors n))))