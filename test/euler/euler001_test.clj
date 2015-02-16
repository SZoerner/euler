(ns euler.euler001-test
  (:require [clojure.test :refer [deftest]]
            [clojure.test.check.generators :as gen]
            [euler.core-test :refer :all]
            [euler.euler001 :refer :all])
  (:use midje.sweet))

(deftest euler001-tests
  (fact "Problem 1"
        (p001 5 3 5) => 3
        (p001 10 3 5) => 23
        (p001) => 233168)

  (fact "Problem 2"
        (p002 35) => 44
        (p002) => 4613732
        (fact-qc "always returns an even result"
                 [n gen/nat]
                 (p002 n) => even?))

  (fact "Problem 3"
        (p003 13195) => 29
        (p003) => 6857)

  (fact "Problem 4"
        (p004 100) => 9009
        (p004) => 906609
        (fact-qc "each returned value is indeed a palindrome"
                 [n (gen/such-that #(> % 1) gen/nat)]
                 (let [res (str (p004 n))]
                   res => (apply str (reverse res)))))

  (fact "Problem 5"
        (p005 10) => 2520
        (p005) => 232792560
        (fact-qc "p005-quickcheck"
                 [n gen/int] ; input: numbers
                 (let [res (p005 n)                  ; result: smallest common multiple
                       nums (range 2 (+ 1 n))              ; all divisors
                       not-div-by? (fn [x] (not (= 0 (rem res x))))]
                   (filter not-div-by? nums) => '()))
        )

  (fact "Problem 6"
        (p006 10) => 2640
        (p006) => 25164150)

  (fact "Problem 7"
        (p007 6) => 13
        (p007 10) => 29
        (p007) => 104743)  

  (fact "Problem 8"
        (p008 5 123456789) => 15120
        (p008) => 40824)

  (fact "Problem 9"
        (p009 (+ 3 4 5)) => (* 3 4 5)
        (p009) => (* 200 375 425)
        (fact-qc "all Pythagorean triplets up to 300"
                 [[a b c] (gen/elements '((3 4 5) (5 12 13) (8 15 17) (7 24 25) (140 171 221)
                                                  (20 21 29) (12 35 37) (9 40 41) (28 45 53)
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
                                                  ))]
                 (= (* a b c) (p009 (+ a b c)))))

  (fact "Problem 10"
        (p010 10) => 17
        (p010) => 142913828922)
)