(ns euler.euler001-quickcheck
  (:require [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct :refer (defspec)]
            [euler.euler001 :refer :all]))

(defspec prob-002-quickcheck ; the name of the test
  100 ; the number of iterations for test.check to test
  (prop/for-all [n gen/nat]
                (even? (prob-002 n)))) ; always returns an even result

(defspec prob-002-lazy-quickcheck
  100
  (prop/for-all [n gen/nat]
                (= (prob-002 n) (prob-002-lazy n)))) ; both algorithms return the same results

(defspec prob-003-lazy-quickcheck
  100
  (prop/for-all [n (gen/such-that #(> % 1) gen/nat)]
                (= (prob-003 n) (prob-003-lazy n)))) ; both algorithms return the same results

(defspec prob-004-quickcheck
  100 
  (prop/for-all [n (gen/such-that #(> % 1) gen/nat)]
                (let [res (str (prob-004 n))]
                  (= res (apply str (reverse res)))))) ; each returned value is indeed a palindrome

(defspec prob-005-quickcheck
  100
  (prop/for-all [n (gen/elements (vec (range 23)))]
                (let [res (prob-005 n)
                      nums (range 2 (+ 1 n))
                      div-by? (fn [x] (= 0 (rem res x)))]
                  (every? div-by? nums))))

(defspec prob-009-quickcheck
  100
  (let [triplets ;; all Pythagorean triplets up to 300
        '((3 4 5 ) (5 12 13) (8 15 17) (7 24 25) (140 171 221)
          (20 21 29) (12 35 37) ( 9 40 41) (28 45 53)
          (11 60 61) (16 63 65) (33 56 65) (48 55 73) (115 276 299)
          (13 84 85) (36 77 85) (39 80 89) (39 252 255) (65 72 97)
          (20 99 101) (60 91 109) (15 112 113) (44 117 125)
          (55 132 143) (17 144 145) (24 143 145) (51 140 149)
          (85 132 157) (52 165 173) (19 180 181) (68 285 293)
          (57 176 185) (104 153 185) (95 168 193) (28 195 197)
          (84 187 205) (84 288 300) (133 156 205) (21 220 221) 
          (23 264 265) (96 247 265) (69 260 269) (115 252 277)
          (60 221 229) (32 255 257) (102 136 170) (95 228 247)
          ; (88 105 137) -> duplicate of '(55 132 143)
          ; (105 208 233) -> duplicate of '(39 252 255)
          ; (119 120 169) -> duplicate of '(102 136 170)
          ; (120 209 241) -> duplicate of '(95 228 247)
          ; (160 231 281) -> duplicate of '(84 288 300) 
          ; (161 240 289) -> duplicate of '(115 276 299)
          )]
    (prop/for-all [[a b c] (gen/elements triplets)]
                  (= (* a b c) (prob-009 (+ a b c))))))