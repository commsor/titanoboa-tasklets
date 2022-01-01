(defproject io.titanoboa.tasklet/http-client "0.1.1"
  :description "http client step function for titanoboa.io"
  :url "https://titanoboa.io"
  :license {:name "unlicense"
            :url "http://unlicense.org/"}
  :dependencies [[clj-http "3.12.3"]]
  :source-paths ["src/clj"]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.10.0"]]}
             :provided {:dependencies [[org.clojure/clojure "1.10.0"]]}})
