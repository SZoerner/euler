(ns euler.helper)

(defn factor?
  "*Int, Int -> Bool*  
  Predicate that tests whether the divisor evenly divides the dividend."
  [dividend divisor]
  (zero? (mod dividend divisor)))

(defn factor-any
  "*(Int) -> Int -> Bool*  
  Returns a predicate that tests whether its argument 
  can be evenly divided by any of the divisors."
  [& divisors]
  (fn [argument]
    (boolean (some #(factor? argument %) divisors))))

;; returns a list of factors
(defn factors [n]
  (let [lower (filter #(factor? n %)
                      ;; calculate up to sqrt(n)
                      (range 1 (inc (Math/sqrt n))))
        ;; add the coresponding pairs by division
        upper (map #(/ n %) lower)]
    (set (concat lower upper))))

(def primes 
  "Lazy stream of prime numbers. Uses the sieve of Eratosthenes algorithm.  
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

;; predicate checking for prime numbers
(defn prime? [n]
  (= (count (factors n)) 2))

;; iterative approach
(defn get-primes
  ;; entry point - one parameter
  ([n]
    ;; start with 2 and empty list
   (get-primes n 2 '()))
  ;; overloaded function
  ([n p primes]
   (cond
      ;; no more factors - stop iteration
     (< n p) primes
      ;; if factor and prime (fun fact: 10x speedup changing the order)
     (and (factor? n p) (prime? p))
      ;; add to set of prime factors
     (get-primes (/ n p) p (conj primes p))
      ;; no match - next iteration
     :else (get-primes n (inc p) primes))))

; (defn prime-factors 
; "Given a number, returns the list of prime factors of n.  
; Example: (prime-factors 12) => (2 2 3)"
; 	[n]
; 	(letfn [(step [div factors [car cdr]]
; 		(cond 
; 			(< div car) factors
; 			(= 1 div) factors
; 			(factor? div car) (recur (/ div car) (conj car factors) '(car cdr))
; 			:else (recur div factors cdr)))]
; 		(step n [] (take-while #(< % n) primes))))

