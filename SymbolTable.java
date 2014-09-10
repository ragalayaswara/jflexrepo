import java.util.ArrayList;
import java.util.*;

public class SymbolTable{
	static ArrayList<String> symtab = new ArrayList<String>();
	

	public boolean enter(String s)
	{
           symtab.add(s);
           return true; 
	}
	/*public boolean lookup(String s)
	{
	  int ind;
	 boolean ps=false;  
	 for (ind=0;ind<=symtab.size();ind++)
	  {
		String element=symtab.get(ind);
		if(element.equals(s))
		 return ps=false;
		else
		 return ps=true;

	  }
 	return ps;
	}*/

	public String toString()
	{
	  String res = "---Symbol Table----\n";
	  for(int i=0;i<symtab.size();i++)
	  	res=res+" \n "+symtab.get(i);
	  System.out.println("\t"+res);
         return res; 
	}
        
	public boolean Check_for_symbol(String remove)
	{
		boolean value=false;
		for(int i=0;i<symtab.size();i++) 
   		{
			if(remove.equals(symtab.get(i)))
           		value=true;
   		}
  		return value;
       }

}
