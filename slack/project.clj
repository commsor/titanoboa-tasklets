(defproject io.titanoboa.tasklet/slack "0.0.1"
  :description "slack client step functions for titanoboa.io"
  :url "https://titanoboa.io"
  :license {:name "unlicense"
            :url "http://unlicense.org/"}
  :dependencies []
  :source-paths ["src/clj"]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.10.0"]
                                  [clj-http "3.12.3"]
                                  [cheshire "5.9.0"]]}
             :provided {:dependencies [[org.clojure/clojure "1.10.0"]
                                       [clj-http "3.12.3"]
                                       [cheshire "5.9.0"]]}})
