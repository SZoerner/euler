(ns euler.euler011-test
  (:require [clojure.test :refer [deftest]]
            [clojure.test.check.generators :as gen]
            [euler.core-test :refer :all]
            [euler.euler011 :refer :all])
  (:use midje.sweet))

(deftest euler011-tests
  (fact "Problem 11"
    (p011) => 70600674)

  ; (fact "Problem 12"
  ;   (p012) => 76576500) ;; too slow - makes cloverage abort
  
  (fact "Problem 13"
    (p013) => 5537376230342)

  (fact "Problem 14"
    (p014 10) => 9
    (p014 100) => 97
    (p014 1000) => 871
    ; (p014) =future=> 837799
    ) ;; too slow - makes cloverage abort
    
  (fact "Problem 15"
    (p015) => 137846528820)

  (fact "Problem 16"
    (p016) => 1366)

  (fact "Problem 17"
    (p017 10) => 39
    (p017) => 21124)

  (fact "Problem 18"
    (p018) => 1074)

  (fact "Problem 19"
    (p019) => 171)

  (fact "Problem 20"
    (p020) => 648))
