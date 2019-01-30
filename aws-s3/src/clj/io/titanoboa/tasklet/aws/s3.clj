 (ns io.titanoboa.tasklet.aws.s3
     (:require [amazonica.aws.s3 :as s3]
               [amazonica.aws.s3transfer :as s3transfer]))


(defn read [{:keys [credentials bucket key]}]
    {:s3-object (-> (s3/get-object credentials bucket key)
                    :input-stream
                    slurp)})

(defn download [{:keys [credentials save-as bucket key]}]
    ((-> (s3transfer/download credentials  bucket key
                                            (java.io.File. titanoboa.exp/*jobdir* (if-not (empty? save-as) save-as  key)))
         :wait-for-completion)))

(defn upload [{:keys [credentials file-name bucket key]}]
    ((-> (amazonica.aws.s3transfer/upload credentials bucket key
                                          (java.io.File. titanoboa.exp/*jobdir* (if-not (empty? file-name) file-name  key)))
         :wait-for-completion)))