# Brainfuck Transpilers
This is a collection of brainfuck transpilers written in Clojure. The word "transpiler" is to be used lightly though,
as they all follow the primitive principal of basically simulating a brainfuck environment with hardcoded instructions
in the target language, instead of transpiling into "real" code.

But as they do produce valid code in the target language that does the same thing as the given brainfuck code they are actually transpilers.
This repository contains examle brainfuck code in the "examples" directory which I verfied will work with all of the transpilers.

A transpiled "Hello World" program is included for every language.
The currently supported Laguages are
- Java
- C
- HTML (embedded JavaScript)

## Requirements to run
- Clojure
- Java
- GCC
- Any POSIX compliant Shell (e.g. Bash)
