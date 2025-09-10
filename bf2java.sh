#!/bin/sh
SOURCE=$1;
if [ ! -f "$SOURCE" ] ; then
		echo "Usage: bf2java [source.bf]";
		exit 65;
fi
CONFIG="$PWD/configs/config-java.edn";
FILE=$(clojure -M bft.clj "$SOURCE" "$CONFIG");
javac "$FILE"
java Main;
rm "$FILE" Main.class;
