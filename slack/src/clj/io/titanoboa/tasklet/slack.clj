(ns io.titanoboa.tasklet.slack
  (:require [clj-http.client :as client]))

(defn get-channel-info [{:keys [token channel response-property-name] :as properties}]
  "Returns channel's information for given Slack channel id. These are returned as :properties in the return map.
  :return-code in the properties map is true if the call was successful, false otherwise."
  (let [response (client/request {:url "https://slack.com/api/conversations.info"
                                  :request-method :get
                                  :as :json
                                  :content-type :x-www-form-urlencoded
                                  :query-params {"channel" channel}
                                  :headers {"Authorization" (str "Bearer " token)}})]
    {:properties {response-property-name (:body response)}
     :return-code (get-in response [:body :ok])}))

(defn get-chat-permalink [{:keys [token channel message_ts response-property-name] :as properties}]
  "Returns permalink for given Slack chat timestamp. It is returned as :properties in the return map.
  :return-code in the properties map is true if the call was successful, false otherwise."
  (let [response (client/request {:url "https://slack.com/api/chat.getPermalink"
                                  :request-method :get
                                  :as :json
                                  :content-type :x-www-form-urlencoded
                                  :query-params {"channel" channel
                                                 "message_ts" message_ts}
                                  :headers {"Authorization" (str "Bearer " token)}})]
    {:properties {response-property-name (:body response)}
     :return-code (get-in response [:body :ok])}))
