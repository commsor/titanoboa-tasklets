(ns io.titanoboa.tasklet.kafka
    (:require [dvlopt.kafka :as K]
      [dvlopt.kafka.in :as K.in]
      [dvlopt.kafka.out :as K.out]))

(defn produce [{:keys [kafka-producer-config records] :as p}]
      (with-open [producer (K.out/producer kafka-producer-config)]
                 (K.out/trx-init producer)
                 (K.out/trx-begin producer)
                 (mapv #(K.out/send producer
                                    {::K/topic (:topic %)
                                     ::K/key   (:key %)
                                     ::K/value (:value %)})
                       records)
                 (K.out/trx-commit producer)))

(defn consume [{:keys [kafka-consumer-config kafka-topics poll-options] :as p}]
      (with-open [consumer (K.in/consumer kafka-consumer-config)]
                 (K.in/register-for consumer kafka-topics)
                 (let [records (->> (K.in/poll consumer poll-options)
                                    (mapv #(select-keys % [::K/offset ::K/partition ::K/topic ::K/timestamp ::K/value ::K/key])))]
                      (K.in/commit-offsets consumer)
                      {:received-records records})))
