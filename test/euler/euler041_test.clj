(ns euler.euler041-test
  (:require [clojure.test :refer [deftest is]]
            [euler.euler041 :as e041]))

(deftest problem-041
  (is (= 7652413 (e041/p041))))

(deftest problem-042
  (is (= 162 (e041/p042))))

(deftest problem-043
  (is (= 16695334890 (e041/p043))))
