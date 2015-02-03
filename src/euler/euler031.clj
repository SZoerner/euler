(ns euler.euler031)

;; # Problem 31 - Coin sums
;; 
;; **Description:** 
;; In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:
;;
;; 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
;; It is possible to make £2 in the following way:
;;
;; 1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
;;
;; **Task:**
;; How many different ways can £2 be made using any number of coins?

(defn p031 [n]
  (let [step
        (fn step [n coins]
          (cond (zero? n) 1                                 ; valid combination
                (neg? n) 0                                  ; no valid combination
                :else (if (< n (first coins))               ; first item is bigger than n
                          (step n (rest coins))
                          (reduce + (map #(step n %) coins)))))]
    (step n '(200 100 50 20 10 5 2 1))))