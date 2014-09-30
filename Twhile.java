//Class to parse while expressions

import java.util.*;

class Twhile extends Texp implements AST {
  Texp exp1; 
  Tboolexp boolexp;           
  String kind;                  

  public Twhile(Tboolexp e1, Texp e2) {
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
    
  }

  public String toString() {
    return "while "+boolexp+" do "+exp1;
  }

}
