(defproject euler "0.1.0-SNAPSHOT"
  :description "My Project Euler solutions, written in Clojure"
  :url "https://github.com/SZoerner/euler"
  :license {:name "MIT License"
            :url  "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [midje "1.8.3"]
                 [org.clojure/test.check "0.9.0"]]
  :plugins [[lein-cloverage "1.0.7-SNAPSHOT"]
            [lein-test-out "0.3.1" :exclusions [org.clojure/clojure]]]
  :profiles {:dev {:injections [(require 'flare.clojure-test)
                                (flare.clojure-test/install!)]}}
  :lein-release {:deploy-via :clojars}
  :marginalia {:css ["marg.css"]
               :javascript ["http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"]
               :dir ["doc"]}
  :codox {:defaults {:doc/format :markdown}
          :sources ["src"]
          :output-dir "doc/codox"
          :src-dir-uri "https://github.com/SZoerner/euler/blob/master/"
          :src-linenum-anchor-prefix "L"})
