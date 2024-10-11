package terminacondos;

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
    "2" { 
        return new Symbol(Simbolo.dos,yytext());
    }
    [0123456789] { 
        return new Symbol(Simbolo.numero,yytext());
    }
    
    . { 
        error(yytext());
    }
}

