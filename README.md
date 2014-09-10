jflexrepo
=========

Developed a parser tool to check grammer and data flow analysis of a code snippet. Tool was developed using java and jflex for parsing a C-like code snippet.
This code creates an internal AST(Abstract Syntax Tree) by parsing the input code and does a live variable analysis of the sample input code and prints the live variables and live variable equations.
Following is the live variable analysis:

Kill (B)  : {variables defined in B} B is a block
Gen (b)  : {variables used before a redefinition in B}

In(B) = (Out(B) - Kill(B)) U Gen(B)

Main.java- consists of the function call to build the symbol table and abstract syntax tree.

Tprogram.java-Consists of logic to calculate the Kills and Gens in the sample input and creating the LV equations

Texpinfix.java, IfThenElseClause.java- these files sample files which show the processing on infix and if then clause expressions to calculate the live variables.

SymbolTable.java- Gives the list of symbols in the input.
