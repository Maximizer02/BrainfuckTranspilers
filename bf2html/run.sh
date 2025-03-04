#!/bin/sh
clojure -M bf2html.clj main.html "$(cat $1)"
