 (ns io.titanoboa.tasklet.aws.ses
     (:require [amazonica.aws.simpleemail]))

(defn send-email [{:keys [credentials to from message]}]
    (amazonica.aws.simpleemail/send-email credentials :destination {:to-addresses to}
                                          :source from
                                          :message message))

