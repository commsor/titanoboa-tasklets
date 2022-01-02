(ns io.titanoboa.tasklet.slack
  (:require [clj-http.client :as client]))

(defn get-channel-info [{:keys [token channel response-property-name] :as properties}]
  "Returns channel's information for given Slack channel id"
  (let [response (client/request {:url "https://slack.com/api/conversations.info"
                                  :request-method :get
                                  :as :json
                                  :content-type :x-www-form-urlencoded
                                  :query-params {"channel" channel}
                                  :headers {"Authorization" (str "Bearer " token)}})]
    {:properties {response-property-name (:body response)}
     :return-code (get-in response [:body :ok])}))
