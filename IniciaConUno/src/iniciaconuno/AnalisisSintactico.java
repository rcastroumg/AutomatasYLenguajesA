
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615
//----------------------------------------------------

package iniciaconuno;

import java_cup.runtime.Symbol;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class AnalisisSintactico extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return Simbolo.class;
}

  /** Default constructor. */
  @Deprecated
  public AnalisisSintactico() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public AnalisisSintactico(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public AnalisisSintactico(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\006\000\002\002\004\000\002\002\003\000\002\003" +
    "\004\000\002\003\003\000\002\004\003\000\002\004\003" +
    "" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\010\000\004\004\005\001\002\000\004\002\012\001" +
    "\002\000\010\002\ufffe\004\ufffe\005\ufffe\001\002\000\010" +
    "\002\000\004\010\005\007\001\002\000\010\002\ufffd\004" +
    "\ufffd\005\ufffd\001\002\000\010\002\ufffc\004\ufffc\005\ufffc" +
    "\001\002\000\010\002\uffff\004\uffff\005\uffff\001\002\000" +
    "\004\002\001\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\010\000\006\002\003\003\005\001\001\000\002\001" +
    "\001\000\002\001\001\000\004\004\010\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$AnalisisSintactico$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$AnalisisSintactico$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$AnalisisSintactico$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



    String codigo;

    @Override
    public void syntax_error(Symbol s) {
        System.out.println("Error en simbolo: "+s.value);
    }


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$AnalisisSintactico$actions {


    //AQUI VA CODIGO EN JAVA
    int contador = 0;

  private final AnalisisSintactico parser;

  /** Constructor */
  CUP$AnalisisSintactico$actions(AnalisisSintactico parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$AnalisisSintactico$do_action_part00000000(
    int                        CUP$AnalisisSintactico$act_num,
    java_cup.runtime.lr_parser CUP$AnalisisSintactico$parser,
    java.util.Stack            CUP$AnalisisSintactico$stack,
    int                        CUP$AnalisisSintactico$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$AnalisisSintactico$result;

      /* select the action based on the action number */
      switch (CUP$AnalisisSintactico$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= L EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.elementAt(CUP$AnalisisSintactico$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.elementAt(CUP$AnalisisSintactico$top-1)).right;
		Nodo start_val = (Nodo)((java_cup.runtime.Symbol) CUP$AnalisisSintactico$stack.elementAt(CUP$AnalisisSintactico$top-1)).value;
		RESULT = start_val;
              CUP$AnalisisSintactico$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.elementAt(CUP$AnalisisSintactico$top-1)), ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$AnalisisSintactico$parser.done_parsing();
          return CUP$AnalisisSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // L ::= I 
            {
              Nodo RESULT =null;
		int lleft = ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.peek()).left;
		int lright = ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.peek()).right;
		Nodo l = (Nodo)((java_cup.runtime.Symbol) CUP$AnalisisSintactico$stack.peek()).value;
		
    l.codigoGraphviz += "nodo"+contador+" [label=L];\n";
    l.codigoGraphviz += "nodo"+contador+" -> "+l.codigoNodo+";\n";
    parser.codigo = l.codigoGraphviz;

              CUP$AnalisisSintactico$result = parser.getSymbolFactory().newSymbol("L",0, ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalisisSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // I ::= I N 
            {
              Nodo RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.elementAt(CUP$AnalisisSintactico$top-1)).left;
		int iright = ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.elementAt(CUP$AnalisisSintactico$top-1)).right;
		Nodo i = (Nodo)((java_cup.runtime.Symbol) CUP$AnalisisSintactico$stack.elementAt(CUP$AnalisisSintactico$top-1)).value;
		int nnleft = ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.peek()).left;
		int nnright = ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.peek()).right;
		Nodo nn = (Nodo)((java_cup.runtime.Symbol) CUP$AnalisisSintactico$stack.peek()).value;
		
    Nodo n = new Nodo();
    n.codigoGraphviz = i.codigoGraphviz+nn.codigoGraphviz;
    n.codigoGraphviz += "nodo"+contador+" [label=I];\n";
    n.codigoNodo = "nodo"+contador;
    n.codigoGraphviz += n.codigoNodo+" -> "+i.codigoNodo+";\n";
    n.codigoGraphviz += n.codigoNodo+" -> "+nn.codigoNodo+";\n";
    contador++;
    RESULT = n;

              CUP$AnalisisSintactico$result = parser.getSymbolFactory().newSymbol("I",1, ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.elementAt(CUP$AnalisisSintactico$top-1)), ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalisisSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // I ::= uno 
            {
              Nodo RESULT =null;
		int uleft = ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.peek()).left;
		int uright = ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.peek()).right;
		String u = (String)((java_cup.runtime.Symbol) CUP$AnalisisSintactico$stack.peek()).value;
		
    Nodo n = new Nodo();
    n.codigoGraphviz = "nodo"+contador+" [label="+u+"];\n";
    String temp = "nodo"+contador;
    contador++;
    n.codigoGraphviz += "nodo"+contador+" [label=I];\n";
    n.codigoNodo = "nodo"+contador;
    n.codigoGraphviz += n.codigoNodo+" -> "+temp+";\n";
    contador++;
    RESULT = n;

              CUP$AnalisisSintactico$result = parser.getSymbolFactory().newSymbol("I",1, ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalisisSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // N ::= numero 
            {
              Nodo RESULT =null;
		int numleft = ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.peek()).left;
		int numright = ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.peek()).right;
		String num = (String)((java_cup.runtime.Symbol) CUP$AnalisisSintactico$stack.peek()).value;
		
    Nodo n = new Nodo();
    n.codigoGraphviz = "nodo"+contador+" [label="+num+"];\n";
    String temp = "nodo"+contador;
    contador++;
    n.codigoGraphviz += "nodo"+contador+" [label=N];\n";
    n.codigoNodo = "nodo"+contador;
    n.codigoGraphviz += n.codigoNodo+" -> "+temp+";\n";
    contador++;
    RESULT = n;

              CUP$AnalisisSintactico$result = parser.getSymbolFactory().newSymbol("N",2, ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalisisSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // N ::= uno 
            {
              Nodo RESULT =null;
		int uleft = ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.peek()).left;
		int uright = ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.peek()).right;
		String u = (String)((java_cup.runtime.Symbol) CUP$AnalisisSintactico$stack.peek()).value;
		
    Nodo n = new Nodo();
    n.codigoGraphviz = "nodo"+contador+" [label="+u+"];\n";
    String temp = "nodo"+contador;
    contador++;
    n.codigoGraphviz += "nodo"+contador+" [label=N];\n";
    n.codigoNodo = "nodo"+contador;
    n.codigoGraphviz += n.codigoNodo+" -> "+temp+";\n";
    contador++;
    RESULT = n;

              CUP$AnalisisSintactico$result = parser.getSymbolFactory().newSymbol("N",2, ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalisisSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalisisSintactico$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$AnalisisSintactico$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$AnalisisSintactico$do_action(
    int                        CUP$AnalisisSintactico$act_num,
    java_cup.runtime.lr_parser CUP$AnalisisSintactico$parser,
    java.util.Stack            CUP$AnalisisSintactico$stack,
    int                        CUP$AnalisisSintactico$top)
    throws java.lang.Exception
    {
              return CUP$AnalisisSintactico$do_action_part00000000(
                               CUP$AnalisisSintactico$act_num,
                               CUP$AnalisisSintactico$parser,
                               CUP$AnalisisSintactico$stack,
                               CUP$AnalisisSintactico$top);
    }
}

}
