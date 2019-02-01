(defproject io.titanoboa.tasklet/aws-sns "0.1.0"
  :description "aws sns step functions for titanoboa.io"
  :url "https://titanoboa.io"
  :license {:name "unlicense"
            :url "http://unlicense.org/"}
  :source-paths ["src/clj"]
  :aot [io.titanoboa.tasklet.aws.sns]
  :dependencies [[amazonica "0.3.117" :exclusions [com.amazonaws/aws-java-sdk
                                                   com.amazonaws/amazon-kinesis-client
                                                   com.taoensso/nippy]]
                 [com.amazonaws/aws-java-sdk-sns "1.11.237"]]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.10.0"]]}
             :provided {:dependencies [[org.clojure/clojure "1.10.0"]]}})
