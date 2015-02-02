(ns euler.core-test
  (:require [clojure.test.check :as qc]
            [clojure.test.check.properties :as prop]))

(defn show-result [description result]
  (when-not (:result result)
    (println (str "\"" description "\"") "\n\t" result)))

(defmacro fact-qc [description props & rest]
  `(~show-result ~description
                 (qc/quick-check
                  100
                  (prop/for-all
                   ~props
                   (midje.sweet/fact ~description ~@rest)))))