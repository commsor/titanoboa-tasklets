(defproject io.titanoboa.tasklet/pdf "0.1.0"
  :description "pdf generation step function for titanoboa.io"
  :url "https://titanoboa.io"
  :license {:name "unlicense"
            :url "http://unlicense.org/"}
  :dependencies [[clj-pdf "2.2.33"]]
  :source-paths ["src/clj"]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.10.0"]]}
             :provided {:dependencies [[org.clojure/clojure "1.10.0"]]}})
