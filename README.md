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

Tboolexp.java- The class which parses boolean expressions

IfThenElseClause.java- The class which parses if else conditions and also updates the LV entry and exit in front and rear of the if else block.

my_cup.cup- Contains the grammar for the language
my_jflex.flex-Contains the lexical symbols for the grammar
sym.java and parser.java- these files are generated from the cup and flex files and used to generate the abstract syntax tree 

My contribution in the above project is as follows:
-Grammar for some expressions
-Parsing if else and while loop conditions and creating the abstract syntax tree
-Doing the live variable analysis for assignment, infix expressions and if else blocks and printing the equations.
