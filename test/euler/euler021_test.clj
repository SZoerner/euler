(ns euler.euler021-test
  (:require [clojure.test :refer [deftest is]]
            [euler.euler021 :as e021]))

(deftest problem-021
  (is (= (e021/p021) 31626)))

; (deftest problem-022
;   (is (= (e021/p022) 871198282)))

; (deftest problem-023
;   (is (= (e021/p023 [12]) 12))
;   (is (= (e021/p023 (range 25)) 276))
;   (is (= (e021/p023) 4179871)))

; (deftest problem-024
;   (is (= (e021/p024) '(2 7 8 3 9 1 5 4 6 0))))

; (deftest problem-025
;   (is (= (e021/p025) 4782)))

; (deftest problem-026
;   (is (= (e021/rec-cycle 1 6) '(6)))
;   (is (= (e021/rec-cycle 1 7) '(1 4 2 8 5 7)))
;   (is (= (e021/p026) 983)))

; (deftest problem-027
;   (is (= (e021/consec-primes 1 41) 40))           ; n² + n + 41
;   (is (= (e021/consec-primes -79 1601) 80))       ; n² − 79n + 1601
;   (is (= (e021/p027) -59231)))

; (deftest problem-028
;   (is (= (e021/p028 3) 25))
;   (is (= (e021/p028 5) 101))
;   (is (= (e021/p028) 669171001)))

; (deftest problem-029
;   (is (= (e021/p029 5) 15))
;   (is (= (e021/p029) 9183)))

; (deftest problem-030
;   (is (= (e021/p030 4) 19316))
;   (is (= (e021/p030) 443839)))
