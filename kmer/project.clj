(defproject io.titanoboa.tasklet/kmer "0.1.0"
  :description "kmer counting step functions for titanoboa.io"
  :url "https://titanoboa.io"
  :license {:name "unlicense"
            :url "http://unlicense.org/"}
  :dependencies []
  :source-paths ["src/clj"]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.10.0"]]}
             :provided {:dependencies [[org.clojure/clojure "1.10.0"]]}}
  :deploy-repositories [["releases" {:sign-releases false :url "https://clojars.org/repo"}]
                        ["snapshots" {:sign-releases false :url "https://clojars.org/repo"}]])
