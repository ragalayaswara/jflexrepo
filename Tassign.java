//Class to parse assignment statements

import java.util.*;

class Tassign extends Texp implements AST {
  Texp exp;            
  Tident id1; 
  Tident id2; 
  Tnumber n1;
  String kind;   
  static int flag=1; 
  String ret;

  public Tassign(){}

 static ArrayList<String> defsvar = new ArrayList<String> ();

  public Tassign(Tident ID, String k, Texp e) {
    id1=ID;
    kind=k;
    exp=e;
  defsvar.add(ID.toString());
  flag=1;
  Tprogram.counter++;
  Tprogram.LV_Exit_Table_index.add (new ArrayList<Integer>());
  Tprogram.LV_Exit_Table_index.get(Tprogram.counter -1).add(Tprogram.counter+1);
  }
  
 public Tassign(Tident ID1, String k, Tident ID2){
    id1=ID1;
    kind=k;
    id2=ID2;
    defsvar.add(ID1.toString());
    Texpinfix.useVars.add(new ArrayList<String>());
    Texpinfix.useVars.get(Texpinfix.i).add(ID2.toString());
    Texpinfix.i++;
    flag=2;
    Tprogram.counter++;
    Tprogram.LV_Exit_Table_index.add (new ArrayList<Integer>());
    Tprogram.LV_Exit_Table_index.get(Tprogram.counter -1).add(Tprogram.counter+1);
  }

 public Tassign(Tident ID1, String k, Tnumber n){
    id1=ID1;
    kind=k;
    n1=n;
    defsvar.add(ID1.toString());
    Texpinfix.useVars.add(new ArrayList<String>());
    Texpinfix.useVars.get(Texpinfix.i).add("\u03a6");
    Texpinfix.i++;
    flag=3;
    Tprogram.counter++;
    Tprogram.LV_Exit_Table_index.add (new ArrayList<Integer>());
    Tprogram.LV_Exit_Table_index.get(Tprogram.counter -1).add(Tprogram.counter+1);
  }

  public String toString() {
  if (flag==1)
    ret = id1+"="+exp ; 
  else if (flag==2)
    ret= id1+"="+id2+";";
  else if (flag == 3)
    ret = id1+"="+n1+";" ;
 return ret;
  }
  
public static ArrayList<String> computeDefVars(){
   return defsvar;
  }

}

