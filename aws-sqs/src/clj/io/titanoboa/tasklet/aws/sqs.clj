 (ns io.titanoboa.tasklet.aws.sqs
   (:require [amazonica.aws.sqs :as sqs]))

(defn send-message [{:keys [credentials ] :as properties}]
    (amazonica.aws.sqs/send-message credentials (select-keys properties [:queue-url :message-attributes :message-attributes])))

