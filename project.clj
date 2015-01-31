(defproject euler "0.1.0-SNAPSHOT"
  :description "My Project Euler solutions, written in Clojure"
  :url "https://github.com/SZoerner/euler"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :plugins [[lein-cloverage "1.0.2"]]
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :profiles {:dev {:dependencies [[midje "1.6.3"]
                                  [org.clojure/test.check "0.7.0"]]
                   :plugins [[lein-midje "3.1.3"]]}})
