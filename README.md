jflexrepo
=========

Developed a parser tool to check grammer and data flow analysis of a code snippet. Tool was developed using java and jflex for parsing a C-like code snippet.
This code creates an internal AST by parsing the input code and does a live variable analysis of the sample input code and prints the live variables and live variable equations.
Following is the live variable analysis:

Kill (B)  : {variables defined in B}
Gen (b)  : {variables used before a redefinition in B}

In(B) = (Out(B) - Kill(B)) U Gen(B)
