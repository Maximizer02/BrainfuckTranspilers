#!/bin/sh
SOURCE=$1;
if [ ! -f "$SOURCE" ] ; then
		echo "Usage: bf2c [source.bf]";
		exit 65;
fi
CONFIG="$PWD/configs/c.edn";
FILE=$(clojure -M bft.clj "$SOURCE" "$CONFIG");
OUT=${FILE%.*};
gcc -o "$OUT" -O3 "$FILE";
chmod +x "$OUT";
./"$OUT";
rm "$FILE" "$OUT";
