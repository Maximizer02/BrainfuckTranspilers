#!/home/linuxbrew/.linuxbrew/bin/clj -M
(require '[clojure.java.io :as io])

(when (not= 2 (count *command-line-args*))
  (println "Usage: bf2java [out-name] [code]")  
  (System/exit 65)
  )

(def out-name (first *command-line-args*))
(def code (second *command-line-args*))

(defn write [text]
  (spit out-name text :append true)
)

(def start "class Main{public static void main(String[]args){")
(def setup "int[]t=new int[512];int p=0;")
(def end "}}")

(def plus "t[p]++;")
(def minus "t[p]--;")
(def arrow-right "p++;")
(def arrow-left "p--;")
(def dot "System.out.print((char)t[p]);")
(def bracket-right "while(t[p]>0){")
(def bracket-left "}")

(if (.exists (io/file out-name))
    (io/delete-file out-name)
  )

(write start)
(write setup)
(doseq[c code]
 (write 
   (case c
    \+ plus
    \- minus
    \> arrow-right
    \< arrow-left
    \. dot
    \] bracket-left
    \[ bracket-right
    "")
    )
 )
(write end)
