//Class to handle expression lists

class Texplist implements AST {
  Texplist explist;          
  Texp exp;  
                  

  public Texplist(Texplist p, Texp e) {
    explist=p;
    exp=e;
  }

  public Texplist(Texp e) {
    explist=null;
    exp=e;
  }

  public String toString() {
    if (explist!=null)
      return explist+";"+exp;
    else
      return exp.toString();
 } 

  
}
