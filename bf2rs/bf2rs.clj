#!/home/linuxbrew/.linuxbrew/bin/clj -M
(require '[clojure.java.io :as io])

(when (not= 2 (count *command-line-args*))
  (println "Usage: bf2c [out-name] [code]")  
  (System/exit 65)
  )

(def out-name (first *command-line-args*))
(def code (second *command-line-args*))

(defn write [text]
  (spit out-name text :append true)
)

(def start "fn main(){")

(def setup "let mut t: [i32;512]=[0;512];let mut p:usize=0;")
(def end "}")

(def plus "t[p]+=1;")
(def minus "t[p]-=1;")
(def arrow-right "p+=1;")
(def arrow-left "p-=1;")
(def dot "print!(\"{}\",char::from_u32(t[p] as u32).unwrap());")
(def bracket-right "while t[p]>0 {")
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
