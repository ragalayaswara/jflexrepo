
   
import java_cup.runtime.*;
      
%%

%class Lexer

%line
%column

%cup
   

%extends sym

%{ 

  private Symbol sym(int sym) {
    return new Symbol(sym);
  }

  private Symbol sym(int sym, Object val) {
    return new Symbol(sym, val);
  }
%}



LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
   

number = 0 | [1-9][0-9]*
//letter = [a-zA-Z]




%%
/*Lexical Rules Section*/
   
<YYINITIAL> {

    "else"             { return sym(sym.ELSE); }
    "if"               { return sym(sym.IF); }
    "or"               { return sym(sym.OR); }
    "then"             { return sym(sym.THEN); }
    "int"              { return sym(sym.INT); }
    "bool"             { return sym(sym.BOOLEAN); }
    "str"              { return sym(sym.STRING); }
    "function"         { return sym(sym.FUNCTION); }
    "while"            { return sym(sym.WHILE); }
    "do"               { return sym(sym.DO); }
    ";"                { return sym(sym.SEMI); }
    ","                { return sym(sym.COMMA); }
    "+"                { return sym(sym.PLUS); }
    "-"                { return sym(sym.MINUS); }
    "/"                { return sym(sym.DIVIDE); }
    "*"                { return sym(sym.TIMES); }
    "("                { return sym(sym.LPAREN); }
    ")"                { return sym(sym.RPAREN); }
    "{"                { return sym(sym.LBRACK); }
    "}"                { return sym(sym.RBRACK); }
    "&&"               { return sym(sym.AND); }
    "!"                { return sym(sym.NOT); }
    "||"               { return sym(sym.OR); }
    "="                { return sym(sym.IS); }
    "=="               { return sym(sym.EQ); }
    "<="               { return sym(sym.LEQ); }
    ">="               { return sym(sym.GEQ); }
    ">"                { return sym(sym.GRAT); }
    "<"                { return sym(sym.LESS); }
    "!="               { return sym(sym.NEQ); }
    [a-zA-Z]+          { return sym(sym.ID, yytext()); }
    
    {number}           { System.out.print(yytext());
                         return sym(sym.NUMBER, new Integer(yytext())); }
   
   

    {WhiteSpace}       {  }   
}


[^]                    { throw new Error("Illegal character <"+yytext()+">"); }

