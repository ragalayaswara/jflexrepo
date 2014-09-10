
import java.util.*;

/**
 * AST node for infix expressions
 */ 
class Texpinfix extends Texp implements AST { //creates AST for infix expressions;ex: a+b
  Texp exp1, exp2;            
  String kind;                  
  static ArrayList<ArrayList<String>> useVars = new ArrayList<ArrayList<String>>();
  static int i=0;

  public Texpinfix(){}

  public Texpinfix(Texp e1, String k, Texp e2) {
    exp1=e1;
    kind=k;
    exp2=e2;
    useVars.add(new ArrayList<String>());
    useVars.get(i).add(e1.toString());
    if(! (e1.toString()).equals(e2.toString()))
    useVars.get(i).add(e2.toString());
   i++;
  
  }

  public String toString() {
    return("("+exp1+kind+exp2+")"); 
  }
 
public static ArrayList<ArrayList<String>> computeUseVars(){
   SymbolTable st = new SymbolTable();
   for(int a =0; a<useVars.size();a++){
     for(int j=0;j<((ArrayList)useVars.get(a)).size(); j++){
       if(!useVars.get(a).get(j).equals("\u03a6"))
         if(st.Check_for_symbol(useVars.get( a).get(j)) == false)
           useVars.get(a).remove(j);
      }
   }
   return useVars;
  }

}


