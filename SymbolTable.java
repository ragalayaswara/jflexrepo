import java.util.ArrayList;
import java.util.*;

public class SymbolTable{
	static ArrayList<String> symtab = new ArrayList<String>();
	

	public boolean enter(String s)
	{
           symtab.add(s);
           return true; 
	}

	public String toString()
	{
	  String res = "---Symbol Table----\n";   //creating symbol tables ..recognising symbols in the input program
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
