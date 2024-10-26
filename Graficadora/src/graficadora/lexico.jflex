package graficadora;

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
    "cuadrado" { 
        return new Symbol(Simbolo.cuadrado,yytext());
    }
    "(" { 
        return new Symbol(Simbolo.parAbre,yytext());
    }
    ")" { 
        return new Symbol(Simbolo.parCierra,yytext());
    }
    [0-9]+ { 
        return new Symbol(Simbolo.entero,yytext());
    }
    "," { 
        return new Symbol(Simbolo.coma,yytext());
    }
    ";" { 
        return new Symbol(Simbolo.pComa,yytext());
    }
    "rojo" { 
        return new Symbol(Simbolo.rojo,yytext());
    }
    "azul" { 
        return new Symbol(Simbolo.azul,yytext());
    }
    "rellena" { 
        return new Symbol(Simbolo.rellena,yytext());
    }
    "contorno" { 
        return new Symbol(Simbolo.contorno,yytext());
    }
    (" " | \r | \t | \f)+ { 
        return new Symbol(Simbolo.contorno,yytext());
    }
    . { 
        error(yytext());
    }
}
