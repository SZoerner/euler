(ns euler.euler031-test
  (:require [clojure.test :refer [deftest is]]
            [euler.euler031 :as e031]))

(deftest problem-031
  ; (is (= (e031/p031 1 [1]) 1))
  ; (is (= (e031/p031 3 [1 2]) 2))
  ; (is (= (e031/p031 5 [1 2]) 3))
  (is (= (e031/p031) 73682)))

(deftest problem-032
  (is (= (e031/p032) 45228)))

(deftest problem-033
  (is (= (e031/p033) 100)))

(deftest problem-034
  (is (= (e031/p034) 40730)))

(deftest problem-035
  (is (= (e031/p035) 55)))

(deftest problem-036
  (is (= (e031/p036) 872187)))

(deftest problem-037
  (is (= (e031/p037) 748317)))
