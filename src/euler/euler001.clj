(ns euler.euler001)

; Problem 1 - Multiples of 3 and 5
; -----------------------------------
; If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
; 
; **Task**: Find the sum of all the multiples of 3 or 5 below 1000.

(defn p001 [n]
  (->>
    (range n)                                               ; for all numbers below n
    (filter #(or (zero? (mod % 3))
                 (zero? (mod % 5))))                          ; filter the multiples of 3 or 5
    (reduce +)))                                            ; reduce the resulting list by adding up each element

; calculation
; (p001 1000)


; Problem 2 - Even Fibonacci numbers
; -----------------------------------
; Each new term in the Fibonacci sequence is generated by adding the previous two terms. By starting with 1 and 2, the first 10 terms will be:
; (1), 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
; By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.

; **Background**
; A Fibonacci number (exept 0 and 1) is recursively defined as the sum of its two predecessing Fibonacci numbers:
; - base case: ``fib(n <= 1) = n``
; - rec case: ``fib (n > 1) = fib(n - 1) + fib(n - 2)``

(defn fib                                                   ; ; cached version using an accumulator
  "computes the nth element in the Fibonacci sequence"      ; overloaded function
  ([n]
   (if (zero? n)
     0N
     (fib n 0N 1N)))                      ; with one parameter

  ([n p0 p1]                                                ; with three parameters
   (if (= n 1N)
     p1
     (fib (dec n) p1 (+ p0 p1)))))


; getting all even fib numbers below 4.000.000
; fun fact: the highest is already fib (33) = 3.524.578
(defn p002 [n]
  (reduce +
          (for [num (range 0N (inc n))
                :let [fib-num (fib num)]
                :while (< fib-num n)
                :when (even? fib-num)] fib-num)))

; calculation
; (p002 4000000)


; Lazy implementation
(defn fib-lazy
  "Computing the whole fibonacci sequence - lazily and polymorphic."
  ([] (fib-lazy 1N 1N))                                     ; base case: creates an infinite, lazy seq (aka list)
  ([x y] (cons x (lazy-seq (fib-lazy y (+ x y)))))          ; fib (x, y) -> fib (y, x+y) = infinite, lazy seq starting from x
  ([x] (first (drop (dec x) (take x (fib-lazy))))))         ; retrieve the nth fib element

(defn p002-lazy [n]
  (->>
    (fib-lazy)
    (take-while #(< % n))
    (filter even?)
    (reduce +)))

; calculation
; (p002-lazy 4000000)


; Problem 3 - Largest prime factor
; ---------------------------------
; The prime factors of 13195 are 5, 7, 13 and 29.
; What is the largest prime factor of the number 600851475143 ?

; predicate checking whether num is divisible by div
(defn factor? [num div]
  (zero? (rem num div)))

; returns a list of factors
(defn factors [n]
  (let [lower (filter #(factor? n %)
                      (range 1 (inc (Math/sqrt n))))        ;; calculate up to sqrt(n)
        upper (map #(/ n %) lower)]                         ;; add the coresponding pairs by division
    (set (concat lower upper))))

; predicate checking for prime number
(defn prime? [n]
  (= (count (factors n)) 2))

; predicate checking for prime number (using Java's BigInteger)
(defn prime-java? [n]
  (.isProbablePrime (BigInteger/valueOf n) 5))              ; certainty of 5 - 96.875%


; iterative approach
(defn get-primes
  ([n]                                                      ; entry point - one parameter
   (get-primes n 2 '()))                                    ; start with 2 and empty list
  ([n p primes]                                             ; overloaded function
   (cond
     (< n p) primes                                         ; no more factors - stop iteration
     (and (factor? n p) (prime? p))                         ; if factor and prime (fun fact: 10x speedup changing the order)
     (get-primes (/ n p) p (conj primes p))                 ; add to set of prime factors
     :else (get-primes n (inc p) primes))))                 ; no match - next iteration

(defn p003 [n]
  (apply max (get-primes n)))

; calculation
; (p003 600851475143)


; lazy stream approach
(def lazy-primes
  "lazy stream of prime numbers"
  (filter prime?                                            ; filter primes
          (conj (iterate #(+ 2 %) 3) 2)))                   ; append all odd numbers to 2

(defn max-prime [n primes]
  (let [div (first primes)]                                 ; local variable - head of prime list
    (cond
      (= n div) div                                         ; termination - found max prime
      (factor? n div) (max-prime (/ n div) primes)
      :else (max-prime n (rest primes)))))                  ; list eater

(defn p003-lazy [n]
  (max-prime n lazy-primes))

; calculation
; (p003-lazy 600851475143)


; Problem 4 - Largest palindrome product
; ---------------------------------------
; A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
; Find the largest palindrome made from the product of two 3-digit numbers.

(defn p004 [n]
  (apply max                                                ; get the largest result of
         (for [num1 (range n 0 -1)
               num2 (range n 0 -1)                          ; all numbers from 100 to 1000
               :let [pal (* num1 num2)]                     ; compute the product of the two
               :when (= (str pal)                           ; and filter those who are palindromes
                        (clojure.string/reverse (str pal)))] ; by converting to string and compare to the reversed string
           pal)))

; calculation
;(p004 1000)


; Problem 5 - Smallest multiple
; ------------------------------
; 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
; What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?

; reference for computing the least common multiple
; http://en.wikipedia.org/wiki/Least_common_multiple

(defn p005 [n]
  (->>
    (range (inc n))
    (map #(frequencies (get-primes %)))
    (apply merge-with max)
    (map #(Math/pow (first %) (second %)))
    (reduce *)
    (int)))

; calculation
; (p005 20)


; Problem 6 - Sum square difference
; ----------------------------------
; The sum of the squares of the first ten natural numbers is 1^2 + 2^2 + ... + 10^2 = 385
; The square of the sum of the first ten natural numbers is (1 + 2 + ... + 10)^2 = 55^2 = 3025
; Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.
; Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.

(defn p006 [n]
  (int (- (Math/pow (reduce + (range (inc n))) 2)
          (reduce + (map #(Math/pow % 2) (range (inc n)))))))

; calculation
; (p006 100)

; Problem 7 - 10001st prime
; --------------------------
; By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
; What is the 10 001st prime number?

; type: number => number
(defn p007 [n]
  (->>
    (range 1 Integer/MAX_VALUE)                             ; for all positive integers
    (take-nth 2)                                            ; shortcut to remove even numbers
    (filter prime?)                                         ; only prime numbers left
    (take (dec n))                                          ; realize n items
    last))                                                  ; pick the nth element

; calculation
; (p007 10001)


; Problem 8 - Largest product in a series
; ----------------------------------------
; Find the greatest product of five consecutive digits in the 1000-digit number.

(defn p008 [n series]
  (->>
    (str series)                                            ; hack to retrieve individual digits
    (map #(Integer/parseInt (str %)))                       ; Int => List[Int]
    (partition n 1)                                         ; partition into lists of 5
    (map #(apply * %))                                      ; calculate the product
    (apply max)))                                           ; get the highest product

; calculation
; (p008 5 7316717653133062491922511967442657474235534919493496983520312774506326239578318016984801869478851843858615607891129494954595017379583319528532088055111254069874715852386305071569329096329522744304355766896648950445244523161731856403098711121722383113622298934233803081353362766142828064444866452387493035890729629049156044077239071381051585930796086670172427121883998797908792274921901699720888093776657273330010533678812202354218097512545405947522435258490771167055601360483958644670632441572215539753697817977846174064955149290862569321978468622482839722413756570560574902614079729686524145351004748216637048440319989000889524345065854122758866688116427171479924442928230863465674813919123162824586178664583591245665294765456828489128831426076900422421902267105562632111110937054421750694165896040807198403850962455444362981230987879927244284909188845801561660979191338754992005240636899125607176060588611646710940507754100225698315520005593572972571636269561882670428252483600823257530420752963450)


; Problem 9 - Special Pythagorean triplet
; ----------------------------------------
; A Pythagorean triplet is a set of three natural numbers, a < b < c, for which a^2 + b^2 = c^2
; For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
; There exists exactly one Pythagorean triplet for which a + b + c = 1000.
; Find the product abc.

(defn p009 [n]
  (first (for [a (range n)
               b (range (- n a))
               :let [c (- n (+ a b))]
               :when (and (< a b c)
                          (= (+ (Math/pow a 2) (Math/pow b 2))
                             (Math/pow c 2)))]
           (* a b c))))

; calculation
; (p009 1000)


; Problem 10 - Summation of primes
; ---------------------------------
; The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
; Find the sum of all the primes below two million.

(defn p010 [n]
  (->>
    lazy-primes
    (take-while #(< % n))
    (reduce +)))

; calculation
; (p010 2000000)