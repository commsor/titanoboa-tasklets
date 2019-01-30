 (ns io.titanoboa.tasklet.aws.ec2
     (:require [amazonica.aws.ec2]))

(defn list-instances [{:keys [credentials ] :as properties}]
    {:ec2-instances (->> (amazonica.aws.ec2/describe-instances credentials)
                         :reservations
                         (mapv :instances)
                         (mapcat identity)
                         vec)})


(defn stop-instances [{:keys [credentials instance-ids] :as properties}]
    (amazonica.aws.ec2/stop-instances credentials {:instance-ids instance-ids}))


(defn start-instances [{:keys [credentials instance-ids] :as properties}]
    (amazonica.aws.ec2/start-instances credentials {:instance-ids instance-ids}))