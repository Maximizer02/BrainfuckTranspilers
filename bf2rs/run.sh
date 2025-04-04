#!/bin/sh
clojure -M bf2rs.clj main.rs "$(cat $1)"
rustc -o main -C opt-level=3 main.rs
chmod 777 main
./main
rm main main.rs
