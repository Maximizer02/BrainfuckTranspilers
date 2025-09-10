#!/home/linuxbrew/.linuxbrew/bin/clj -M

(require 'clojure.edn)
(require '[clojure.string :as str])

(when (not= 2 (count *command-line-args*))
  (println "Usage: bft [source.bf] [config.edn]")
  (System/exit 65)
  )

(->> *command-line-args*
     (first)
     (slurp)
     (def source)
     )

(->> *command-line-args*
     (second)
     (slurp)
     (clojure.edn/read-string)
     (def config)
    )

(def infrastructure (config :infrastructure))
(def mappings (config :mappings))
(def out-name 
  (->  *command-line-args*
       (first)
       (str/split #"\/")
       (last)
       (str/split #"\.")
       (drop-last)
       (vec)
       (conj (config :extension))
       (#(str/join "." %))
    )
  )

(defn map-bf[c]
  (case c
    \+ :plus
    \- :minus
    \> :arrow-right
    \< :arrow-left
    \. :dot
    \[ :bracket-left
    \] :bracket-right
    :default)
  )

(defn write[text]
  (spit out-name text :append true)
  )

(write (infrastructure :start))
(write (infrastructure :setup))

(doseq[c source]
 (->> c
     (map-bf)
     (get mappings)
     (write)
     )
  )

(write (infrastructure :end))

(println out-name)
