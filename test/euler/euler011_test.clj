(ns euler.euler011-test
  (:require [clojure.test :refer [deftest]]
            [midje.sweet :refer [fact]]
            [euler.core-test :refer [fact-qc]]
            [euler.euler011 :as e011]))

(deftest euler011-tests
  (fact "Problem 11"
        (e011/e011/p011) => 70600674)

  ; (fact "Problem 12"
  ;       (e011/p012) => 76576500) ;; too slow - makes cloverage abort

  (fact "Problem 13"
        (e011/p013) => 5537376230342)

  (fact "Problem 14"
        (e011/p014 10) => 9
        (e011/p014 100) => 97
        (e011/p014 1000) => 871
        ;; (e011/p014) => 837799
        ) ;; too slow - makes cloverage abort

  (fact "Problem 15"
        (e011/p015) => 137846528820)

  (fact "Problem 16"
        (e011/p016) => 1366)

  (fact "Problem 17"
        (e011/p017 10) => 39
        (e011/p017) => 21124)

  (fact "Problem 18"
        (e011/p018) => 1074)

  (fact "Problem 19"
        (e011/p019) => 171)

  (fact "Problem 20"
        (e011/p020) => 648))
