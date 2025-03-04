#!/home/linuxbrew/.linuxbrew/bin/clj -M
(require '[clojure.java.io :as io])

(when (not
        (some #(= % (count *command-line-args*))[2 3])
        ) 
  (println "Usage: bf2java [out-name] [code] <minify>")  
  (System/exit 65)
  )

(def out-name (first *command-line-args*))
(def code (second *command-line-args*))
(def minify (and
              (= 3 (count *command-line-args*))
              (= "true" (nth *command-line-args* 2))
              )
  )

(defn write [text]
  (spit out-name text :append true)
  (if (not minify)
      (spit out-name "
":append true)
  )
)

(def start "class Main{public static void main(String[] args)")
(def setup (if minify
             "{int[]t=new int[512];int p=0;"
             "{int[] tape = new int[512];int tape_ptr = 0;"
             )
  )
(def end "}}")

(def plus (if minify "t[p]++;" "tape[tape_ptr]++;"))
(def minus (if minify "t[p]--;" "tape[tape_ptr]--;"))
(def arrow-right (if minify "p++;" "tape_ptr++;"))
(def arrow-left (if minify "p--;" "tape_ptr--;"))
(def dot (if minify "System.out.print((char)t[p]);"
                    "System.out.print((char)tape[tape_ptr]);"))
(def bracket-right (if minify "while(t[p]>0){"
                              "while(tape[tape_ptr]>0){")
  )
(def bracket-left"}")

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
