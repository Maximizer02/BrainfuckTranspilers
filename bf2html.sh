#!/bin/sh
SOURCE=$1;
if [ ! -f "$SOURCE" ] ; then
		echo "Usage: bf2c [source.bf]";
		exit 65;
fi
CONFIG="$PWD/configs/html.edn";
clojure -M bft.clj "$SOURCE" "$CONFIG";
