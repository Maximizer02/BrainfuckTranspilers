#!/bin/sh
SOURCE=$1;
if [ ! -f "$SOURCE" ] ; then
		echo "Usage: bf2rs [source.bf]";
		exit 65;
fi
CONFIG="$PWD/configs/rust.edn";
FILE=$(clojure -M bft.clj "$SOURCE" "$CONFIG");
OUT=${FILE%.*};
rustc -o "$OUT" -C opt-level=3 "$FILE"
chmod +x "$OUT";
./"$OUT";
rm "$FILE" "$OUT";
