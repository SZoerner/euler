(defproject euler "0.1.0-SNAPSHOT"
  :description "My Project Euler solutions, written in Clojure"
  :url "https://github.com/SZoerner/euler"
  :license {:name "MIT License"
            :url  "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/test.check "0.9.0"]]
  :plugins [[lein-cloverage "1.0.9"]
            [lein-codox "0.10.8"]
            [lein-marginalia "0.9.2"]]
  :marginalia {:javascript ["http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"]
               :dir ["docs"]}
  :codox {:metadata {:doc/format :markdown}
          :output-path "docs/codox"
          :source-paths ["src"]
          :source-uri "https://github.com/SZoerner/euler/blob/main/{filepath}#L{line}"})
