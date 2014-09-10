import java.util.*;

class IfThenElseClause extends Texp implements AST {
  Texp exp1, exp2; 
  Tboolexp boolexp;           
  String kind;                  

  public IfThenElseClause(Tboolexp e1, Texp e2, Texp e3) { //This constructor keeps a counter of all exit variables in a exit table index
    Tprogram.LV_Exit_Table_index.remove(Tprogram.counter -1);
    Tprogram.LV_Exit_Table_index.remove(Tprogram.counter -2);
    Tprogram.counter=Tprogram.counter-2;
    
    Tprogram.counter++;
    Tprogram.LV_Exit_Table_index.add (new ArrayList<Integer>());
    Tprogram.LV_Exit_Table_index.get(Tprogram.counter -1).add(Tprogram.counter+1);
    Tprogram.LV_Exit_Table_index.get(Tprogram.counter -1).add(Tprogram.counter+2);
    boolexp=e1;
    
    Tprogram.LV_Exit_Table_index.add (new ArrayList<Integer>());
    Tprogram.LV_Exit_Table_index.get(Tprogram.counter).add(Tprogram.counter+3);
    exp1=e2;
    
    Tprogram.LV_Exit_Table_index.add (new ArrayList<Integer>());
    Tprogram.LV_Exit_Table_index.get(Tprogram.counter+1).add(Tprogram.counter+3);
    exp2=e3;
    Tprogram.counter=Tprogram.counter+2;
  }

  public String toString() {
    return "if "+boolexp+" then "+exp1+" else "+exp2;
  }

}
