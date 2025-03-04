#!/bin/sh
rm main*
clj -M bf2c.clj main.c "$(cat $1)"
gcc -o main -O3 main.c
chmod 777 main
./main
