import java.io.*;
public class Main {

  public static void main(String [] args) throws Exception {
    Reader reader = null;
    
    if (args.length == 1) {
      File input = new File(args[0]);
      if (!input.canRead()) {
        System.out.println("Error: could not read ["+input+"]");
      }
      reader = new FileReader(input);
    }
    else {  
     reader = new InputStreamReader(System.in);
    }

    Lexer scanner = new Lexer(reader);   
    SymbolTable symtab = new SymbolTable();               //creates symbol table     
    //scanner.setSymtab(symtab);

    parser parser = new parser(scanner);
    
    Tprogram syntaxtree = null;

    try { 
      syntaxtree = (Tprogram) parser.parse().value;       //parses the input to create the abstract syntax tree
    }    
    catch (Exception e) { 
      e.printStackTrace(); 
    }
  syntaxtree.setSymtabs();
  syntaxtree.setDefVars();
  syntaxtree.setUseVars();
  syntaxtree.printLV();                                   //prints Live Variables
  syntaxtree.printLVEquations();                          //prints Live variable equations
  syntaxtree.createLVTables();
  syntaxtree.printLVEntryExit();
  }
  

  public static void error(String s) {
   System.out.println(s);
 }
}
