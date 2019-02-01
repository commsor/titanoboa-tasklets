(defproject io.titanoboa.tasklet/aws-ses "0.1.0"
  :description "aws ses step functions for titanoboa.io"
  :url "https://titanoboa.io"
  :license {:name "unlicense"
            :url "http://unlicense.org/"}
  :dependencies [[amazonica "0.3.117" :exclusions [com.amazonaws/aws-java-sdk
                                                   com.amazonaws/amazon-kinesis-client
                                                   com.taoensso/nippy]]
                 [com.amazonaws/aws-java-sdk-ses "1.11.237"]]
  :source-paths ["src/clj"]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.10.0"]]}
             :provided {:dependencies [[org.clojure/clojure "1.10.0"]]}})
