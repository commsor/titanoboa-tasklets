(ns io.titanoboa.tasklet.ssh
  (:require [clj-ssh.ssh :as ssh]))


(defn ssh [{:keys [ssh-agent-settings identities host strict-host-key-checking ssh-cmd-map]}]
  (let [agent (ssh/ssh-agent (or ssh-agent-settings {}))]
    (when identities
      (cond
        (map? identities) (ssh/add-identity agent identities)
        (vector? identities) (mapv #(ssh/add-identity agent %) identities)
        :else (throw (java.lang.IllegalStateException. "identities can be either a map or a vector"))))
    (let [session (ssh/session agent host {:strict-host-key-checking strict-host-key-checking})]
      (ssh/with-connection session
                           (ssh/ssh session ssh-cmd-map)))))

(defn sftp [{:keys [ssh-agent-settings identities host strict-host-key-checking sftp-cmds-vec]}]
  (let [agent (ssh/ssh-agent (or ssh-agent-settings {}))]
    (when identities
      (cond
        (map? identities) (ssh/add-identity agent identities)
        (vector? identities) (mapv #(ssh/add-identity agent %) identities)
        :else (throw (java.lang.IllegalStateException. "identities can be either a map or a vector"))))
    (let [session (ssh/session agent host {:strict-host-key-checking strict-host-key-checking})]
      (ssh/with-connection session
                           (let [channel (ssh/ssh-sftp session)]
                             (ssh/with-channel-connection channel
                                                          (mapv #(ssh/sftp channel {} (first %) (second %)) sftp-cmds-vec)))))))
