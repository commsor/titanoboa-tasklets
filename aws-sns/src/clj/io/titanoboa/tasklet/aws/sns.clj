 (ns io.titanoboa.tasklet.aws.sns
     (:require [amazonica.aws.sns :as sns]))

(defn publish [{:keys [credentials ] :as properties}]
    (sns/publish credentials (select-keys properties [:topic-arn :subject :message :message-attributes])))

