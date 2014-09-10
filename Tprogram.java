import java.util.*;

class Tprogram implements AST {

Texplist explist;
Tparlist parlist;
static ArrayList<String> Defs = new ArrayList<String>();
static ArrayList<ArrayList<String>> use = new ArrayList<ArrayList<String>>();
 
static ArrayList<ArrayList<String>> LiveVariables = new ArrayList<ArrayList<String>>();

static int counter=0;
static ArrayList<ArrayList<Integer>> LV_Exit_Table_index= new ArrayList<ArrayList<Integer>>();

ArrayList<ArrayList<String>> LV_exit=new ArrayList<ArrayList<String>>();   //live variables at exit
ArrayList<ArrayList<String>> LV_entry=new ArrayList<ArrayList<String>>();  //Live variables at entry

int key=0;

  public Tprogram(Tparlist p,Texplist e){
     parlist=p;
     explist=e;
  }
 
  public String toString() {
	return (parlist+"\n"+explist);
  }

  SymbolTable inputs;

  public void setSymtabs() {
   parlist.setSymtab();                    //prints all the symbols in the input code. For ex: variable names
   parlist.printSymtab();
   
  }
  
  public ArrayList setDefVars(){
   Tassign assignstmt = new Tassign();
   
   Defs=assignstmt.computeDefVars();
   for(int i =0; i<Defs.size();i++)
   System.out.println("Kills[Assignment_stmt "+i+"]: "+Defs.get(i));  //computes the kill variables
  return Defs;
 }

 public ArrayList<ArrayList<String>> setUseVars(){                
 Texpinfix infix = new Texpinfix();   
 
 use=infix.computeUseVars();
 for(int i =0; i<use.size();i++){
   System.out.println();
   System.out.print("Gens[Assignment_stmt "+i+"] "+": ");                //computes the gen variables
   for(int j=0;j<((ArrayList)use.get(i)).size(); j++){
   System.out.print(use.get(i).get(j)+ " ");}}
  return use;
}

public ArrayList<String> eliminateKill(ArrayList<String> LiveVar, String killVar)
{
    ArrayList<String> removeVars = new ArrayList<String>();
    for(int i=0;i<LiveVar.size();i++)
    {
       boolean flag=false;
		if((LiveVar.get(i)).equals(killVar))
			flag=true;
      if(flag==false)
      {
	removeVars.add(LiveVar.get(i));
      }
    }
  return removeVars;
}

public ArrayList<String> unionGen(ArrayList<String> removeVars, ArrayList<String> UseList)  //creates a union of all gens
{
    ArrayList<String> LV = new ArrayList<String>(UseList);
    SymbolTable st = new SymbolTable();
    for(int i=0;i<removeVars.size();i++)
    { 
	if((st.Check_for_symbol(removeVars.get(i))) == true)
        {
	  boolean flag=false;
    	  for(int j=0;j<UseList.size();j++)
          {
		if((removeVars.get(i)).equals(UseList.get(j)))
 			flag=true;
          }
          if(flag==false)
         {
            LV.add(removeVars.get(i));
         }
       }
    }
   ArrayList<String> TempLV = new ArrayList<String>();
   for(int id=0;id<LV.size();id++)
   {
      if(!(LV.get(id)).equals("\u03a6"));
      TempLV.add(LV.get(id));
   }
   return TempLV;
}

public ArrayList<ArrayList<String>> storeLV()
{
   LiveVariables.add(new ArrayList<String>());
   LiveVariables.get(0).add("\u03a6");
   for(int i=(Defs.size() - 1);i>=0;i--)
   {      
      ArrayList<String> temp = eliminateKill(LiveVariables.get(Defs.size()-1-i),Defs.get(i));
      ArrayList<String> tmp = unionGen(temp,use.get(i));

      LiveVariables.add(tmp) ;//access the whole row
   }
  return LiveVariables;
}
 
public void printLV()
{
   ArrayList<ArrayList<String>> display = storeLV();
   System.out.print("\n"+display);
   System.out.println("\nLive variables at each assignment block\n");
   
   for(int i = 0; i<display.size();i++){
   List<String> x = new ArrayList<String>();
   for(int j=0;j<((ArrayList)display.get(i)).size(); j++){
   x.add(display.get(i).get(j));
     }
   System.out.print("Block "+i +" LV = "+x+"\n");
  }
  
  System.out.println("Live variable equations \n");
  
  for(int i = 0;i<display.size();i++)
    {
	if(i==display.size()-1)
	    System.out.println("{   }");
        else
            System.out.println("{"+display.get(display.size()-i-1)+"} = { {"+display.get(display.size()-i-2)+"} - {"+Defs.get(i)+"} U {"+use.get(i)+"}}");
    }
 
 }

public void createLVTables()
{
   int LV_rows=LV_Exit_Table_index.size();
   for(int ind=0;ind<LV_rows;ind++)
    {   
	
       if(ind == 0)
       {
	LV_exit.add(new ArrayList<String>());
        LV_exit.get(0).add("\u03a6");
        LV_entry.add(use.get(use.size()-1));
       }
       else 
       {

        int row_size=LV_Exit_Table_index.get((LV_rows-1-ind)).size();
        for(int p = 0;p<row_size;p++)
        {  
           if(p==0)
           {
           
           key= LV_Exit_Table_index.get((LV_rows-1-ind)).get(p);
	   
           LV_exit.add(LV_entry.get(LV_rows-key));
           }
           else
           {
           key=LV_Exit_Table_index.get((LV_rows-1-ind)).get(p);
           
           ArrayList<String> temp = unionGen(LV_exit.get(ind),LV_entry.get(LV_rows-key));
           
           LV_exit.remove(ind);
           LV_exit.add(temp);
           }
        }
       
        ArrayList<String> tmp = eliminateKill(LV_exit.get(ind),Defs.get(Defs.size()-1-ind));
        LV_entry.add(unionGen(tmp,use.get(use.size()-1-ind)));
     
      }
            
   }


  for(int a =0; a<LV_exit.size();a++){
  System.out.println();
  System.out.print("LV exit "+a+": ");
  for(int b=0;b<((ArrayList)LV_exit.get(a)).size(); b++){
   System.out.print(LV_exit.get(a).get(b)+ " ");
   }}
  
  for(int a =0; a<LV_entry.size();a++){
  System.out.println();
  System.out.print("LV entry "+a+": ");
  for(int b=0;b<((ArrayList)LV_entry.get(a)).size(); b++){
   System.out.print(LV_entry.get(a).get(b)+ " ");
   }}
}

  public void printLVEntryExit()        //prints LV equations according to the formula for calculating live variables(equations in README file)
  {
    System.out.println("\nLive variable equations are as follows");
    for(int ind=0;ind<LV_exit.size();ind++)
    {
      System.out.println(" LV_entry("+(ind+1)+") = { LV_exit("+(ind+1)+") - {"+Defs.get(ind)+"} } U {"+use.get(ind)+"}");
    }
    
    for(int id=0;id<LV_Exit_Table_index.size();id++)
    {
      if(LV_Exit_Table_index.get(id).size()>1){
      System.out.println(" LV_exit("+(id+1)+") = LV_entry("+(LV_Exit_Table_index.get(id).get(0))+") U LV_entry("+(LV_Exit_Table_index.get(id).get(1))+") ");
     }
     else
      System.out.println(" LV_exit("+(id+1)+") = LV_entry("+(LV_Exit_Table_index.get(id).get(0))+") ");
    }
 }

}
