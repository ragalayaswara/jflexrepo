//Class to handle parameter list

class Tparlist implements AST {
  Tparlist parlist;           
  Tident ident; 
  Type type;   
 

  public Tparlist(Tparlist p,Type t, Tident i) {
    parlist=p;
    type=t;
    ident=i;
  }

  public Tparlist(Type t,Tident i) {
    //parlist=null;
    type=t;
    ident=i;
  }

  public String toString() {
    if (parlist!=null) 
      return parlist+","+type+" "+ident;
    else 
      return type+" "+ident;
  }

  public void setSymtab() {
    SymbolTable st = new SymbolTable();
    boolean isNew = st.enter(ident.toString());
                   
    if (!isNew) Main.error("Variable "+ident+" defined twice!");
    if (parlist!=null) parlist.setSymtab();

  }
  
 public void printSymtab(){
  SymbolTable st = new SymbolTable();
  st.toString();
}
}
