(ns euler.euler041-test
  (:require [clojure.test :refer [deftest is]]
            [euler.euler041 :as e041]))

(deftest problem-041
  (is (= 4231 (e041/p041 4)))
  (is (= 7652413 (e041/p041))))

(deftest problem-042
  (is (= 162 (e041/p042))))

(deftest problem-043
  (is (= 16695334890 (e041/p043))))

(deftest problem-044
  (is (= 5482660 (e041/p044))))

(deftest problem-045
  (is (= 1533776805 (e041/p045))))

(deftest problem-046
  (is (= 5777 (e041/p046))))

(deftest problem-047
  (is (= 134043 (first (e041/p047)))))

(deftest problem-048
  (is (= "9110846700" (e041/p048))))

(deftest problem-049
  (is (= "296962999629" (e041/p049))))

(deftest problem-050
  (is (= 41 (e041/conseq-primes 1e2)))
  (is (= 953 (e041/conseq-primes 1e3)))
  (is (= 9521 (e041/conseq-primes 1e4)))
  (is (= 997651 (e041/p050))))