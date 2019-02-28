(ns io.titanoboa.tasklet.ssh
  (:require [clj-ssh.ssh :as ssh]))


(defn ssh [{:keys [ssh-agent-settings identities host session-options ssh-cmd-map]}]
  (let [agent (ssh/ssh-agent (or ssh-agent-settings {}))]
    (when identities
      (cond
        (map? identities) (ssh/add-identity agent identities)
        (vector? identities) (mapv #(ssh/add-identity agent %) identities)
        :else (throw (java.lang.IllegalStateException. "identities can be either a map or a vector"))))
    (let [session (ssh/session agent host session-options)]
      (ssh/with-connection session
                           (ssh/ssh session ssh-cmd-map)))))

(defn- sftp-execute [channel cmd-vec]
  (apply ssh/sftp channel {} cmd-vec))

(defn- sftp-execute&convert [channel cmd-vec]
  (if (= :ls (first cmd-vec))
    (->> (sftp-execute channel cmd-vec)
         (into [])
         (mapv (fn[i]
                 [(.getFilename i) (.getPermissionsString (.getAttrs i)) (.getSize (.getAttrs i)) (.getMtimeString(.getAttrs i)) (.getMTime (.getAttrs i))])))
    (sftp-execute channel cmd-vec)))

(defn sftp [{:keys [ssh-agent-settings identities host session-options sftp-cmds-vec]}]
  (let [agent (ssh/ssh-agent (or ssh-agent-settings {}))]
    (when identities
      (cond
        (map? identities) (ssh/add-identity agent identities)
        (vector? identities) (mapv #(ssh/add-identity agent %) identities)
        :else (throw (java.lang.IllegalStateException. "identities can be either a map or a vector"))))
    (let [session (ssh/session agent host session-options)]
      (ssh/with-connection session
                           (let [channel (ssh/ssh-sftp session)]
                             (ssh/with-channel-connection channel
                                                          {:sftp-out (mapv #(sftp-execute&convert channel %) sftp-cmds-vec)}))))))
