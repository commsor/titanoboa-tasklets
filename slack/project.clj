(defproject io.titanoboa.tasklet/slack "0.0.1"
  :description "slack client tasklets for titanoboa.io"
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
                                       [cheshire "5.9.0"]]}}
;;  :deploy-repositories [["releases" {:sign-releases false :url "https://clojars.org/repo"}]
;;                        ["snapshots" {:sign-releases false :url "https://clojars.org/repo"}]]
)
