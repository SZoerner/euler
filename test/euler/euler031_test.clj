(ns euler.euler031-test
  (:require [clojure.test :refer [deftest is]]
            [euler.euler031 :as e031]))

(deftest problem-031
  (is (= 1 (e031/p031 1 [1])))
  (is (= 2 (e031/p031 3 [1 2])))
  (is (= 3 (e031/p031 5 [1 2])))
  (is (= 73682 (e031/p031))))

(deftest problem-032
  (is (= 45228 (e031/p032))))

(deftest problem-033
  (is (= 100 (e031/p033))))

(deftest problem-034
  (is (= 40730 (e031/p034))))

(deftest problem-035
  (is (= 55 (e031/p035))))

(deftest problem-036
  (is (= 872187 (e031/p036))))

(deftest problem-037
  (is (= 748317 (e031/p037))))

(deftest problem-038
  (is (= 932718654 (e031/p038))))
