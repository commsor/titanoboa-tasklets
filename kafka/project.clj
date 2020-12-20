(defproject io.titanoboa.tasklet/kafka "0.1.0"
  :description "kafka step functions for titanoboa.io"
  :url "https://titanoboa.io"
  :license {:name "unlicense"
            :url "http://unlicense.org/"}
  :dependencies [[dvlopt/kafka "1.3.1"]]
  :source-paths ["src/clj"]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.10.0"]]}
             :provided {:dependencies [[org.clojure/clojure "1.10.0"]]}}
  :deploy-repositories [["releases" {:sign-releases false :url "https://clojars.org/repo"}]])
