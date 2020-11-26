(ns io.titanoboa.tasklet.kmer)

(defn take-top-kmers [kmer-map n]
"Sort the provided map of k-mers and returns map with only the top n"
(->> kmer-map
             vec
             (sort-by second >)
             (take (or n 10))
              (into {})))

(defn kmer-count [{:keys [fastq-file start end k top-n] :as p}] 
"Performs k-mer count on provided FASTQ file from line number 'start' to line number 'end'. Note that the line numbers are zero-based.
 It is expected that the FASTQ file will be properly formated with sequence data being on the second line in every four-line set.
 Will return top n counts as a map."
 (with-open [br (java.io.BufferedReader. (java.io.InputStreamReader. (java.io.FileInputStream. fastq-file)))] 
  (loop [c (int 0)
         kmers {}]
    (if-let [line (.readLine br)]
      (if (< c end)
        (if (>= c start)
          (if-not (or (= "" line) (not= 1 (mod c 4)) (.contains [">" "@" "+" ";" "~" "!"] (.substring line 0 1)))
            (recur (unchecked-inc c) (merge-with + kmers (frequencies (map #(apply str %) (partition k 1 line)))))
            (recur (unchecked-inc c) kmers))
          (recur (unchecked-inc c) kmers))
        {:kmers (take-top-kmers kmers top-n)})
       {:kmers (take-top-kmers kmers top-n)}))))

(defn split-fastq [{:keys [fastq-file k split-to top-n] :as p}] 
 "Counts the lines in FASTQ file and returns vector of maps with line numbers of start and end for each part if the file is split to 'split-to' parts.
 Since the returned maps are to be used to map/reduce functions in Titanoboa, other properties (fastq-file, top-n, k) are also included in the maps."
  (let [cnt (with-open [br (java.io.BufferedReader. (java.io.InputStreamReader. (java.io.FileInputStream. fastq-file)))] 
                (loop [c (int 0)]
                  (if (.readLine br)
                    (recur (unchecked-inc c))
                    c)))]
    (mapv (fn [i] {:properties {:start (* i (int (Math/ceil (/ cnt split-to)))) :end (* (inc i) (int (Math/ceil (/ cnt split-to)))) :fastq-file fastq-file  :k k :top-n top-n}}) 
                (range split-to))))

(defn reduce-kmers [value item]
  (let [value (or value {}) 
         counts (:kmers item)]
         (update value :kmers #(merge-with + % counts))))
