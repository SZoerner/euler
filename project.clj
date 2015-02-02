(defproject euler "0.1.0-SNAPSHOT"
            :description "My Project Euler solutions, written in Clojure"
            :url "https://github.com/SZoerner/euler"
            :license {:name "MIT License"
                      :url  "http://opensource.org/licenses/MIT"}
            :plugins [[codox "0.8.10"]
                      [lein-midje "3.1.3"]
                      [lein-cloverage "1.0.2"]
                      [lein-marginalia "0.8.0"]]
            :dependencies [[org.clojure/clojure "1.6.0"]]
            :profiles {:dev {:dependencies [[midje "1.6.3"]
                                            [org.clojure/test.check "0.7.0"]]}}
            :marginalia {:css ["marg.css"]}
            :codox {:defaults {:doc/format :markdown}
                    :sources ["src"]
                    :src-dir-uri "https://github.com/SZoerner/euler/blob/master/"
                    :src-linenum-anchor-prefix "L"})
