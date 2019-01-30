(defproject io.titanoboa.tasklet/aws-s3 "0.1.0"
  :description "http client step function for titanoboa.io"
  :url "http://example.com/FIXME"
  :license {:name "unlicense"
            :url "http://unlicense.org/"}
  :dependencies [[amazonica "0.3.117" :exclusions [com.amazonaws/aws-java-sdk
                                                   com.amazonaws/amazon-kinesis-client
                                                   com.taoensso/nippy]]
                 [com.amazonaws/aws-java-sdk-core "1.11.237"]
                 [com.amazonaws/aws-java-sdk-s3 "1.11.237"]]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.10.0"]]}})
