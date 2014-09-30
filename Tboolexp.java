//Class to parse boolean expressions

import java.util.*;

class Tboolexp extends Texp implements AST {
  Texp exp1, exp2;            
  String kind;                  

  public Tboolexp(Texp e1, String k, Texp e2) {
    exp1=e1;
    kind=k;
    exp2=e2;
    Tassign.defsvar.add("\u03a6");
    Texpinfix.useVars.add(new ArrayList<String>());
    Texpinfix.useVars.get(Texpinfix.i).add(e1.toString());
    Texpinfix.useVars.get(Texpinfix.i).add(e2.toString());
    Texpinfix.i++;
  }

  public String toString() {
    return("("+exp1+kind+exp2+")"); 
  }

}

