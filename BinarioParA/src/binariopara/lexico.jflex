package binariopara;

import java_cup.runtime.Symbol;

%%
%cupsym Simbolo
%cup
%class AnalisisLexico
%public
%unicode
%line
%char
%ignorecase

%{
    //VA IR CODIGO EN JAVA
    private void error(String message) {
        //System.out.println("Error en linea "+(yline+1)+",columna "+(yycolumn+1)+" caracter: "+message);
        System.out.println("Error: "+message);
    }
%}

%%

<YYINITIAL>{
    "0" { 
        return new Symbol(Simbolo.cero,yytext());
    }
    "1" { 
        return new Symbol(Simbolo.uno,yytext());
    }
    
    . { 
        error(yytext());
    }
}
