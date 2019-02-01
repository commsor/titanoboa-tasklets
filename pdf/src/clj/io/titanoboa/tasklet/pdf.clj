(ns io.titanoboa.tasklet.pdf
  (:require [clj-pdf.core]))


(defn generate-pdf [{:keys [pdf-metadata pdf-sections file-name]}] 
 (clj-pdf.core/pdf (into [pdf-metadata] pdf-sections) (.getAbsolutePath (java.io.File. (deref (ns-resolve 'titanoboa.exp (symbol "*jobdir*"))) file-name))))
