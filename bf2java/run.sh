#!/bin/sh
clojure -M bf2java.clj main.java "$(cat $1)"
javac main.java
java Main
rm main.java Main.class

