package graficadoraa;

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
    
    private void error(String message) {
    
        System.out.println("Error: "+message);
  }
%}

%%

<YYINITIAL>{


    "cuadrado" { 
        return new Symbol(Simbolo.cuadrado,yytext());
    }
    "linea" { 
        return new Symbol(Simbolo.linea,yytext());
    }
    "rectangulo" { 
        return new Symbol(Simbolo.rectangulo,yytext());
    }
    "circulo" { 
        return new Symbol(Simbolo.circulo,yytext());
    }
    "ovalo" { 
        return new Symbol(Simbolo.ovalo,yytext());
    }
    "trianguloRectangulo" { 
        return new Symbol(Simbolo.trianguloRectangulo,yytext());
    }
    "estrella" { 
        return new Symbol(Simbolo.estrella,yytext());
    }
    "poligono" { 
        return new Symbol(Simbolo.poligono,yytext());
    }
    "pie" {
        return new Symbol(Simbolo.pie,yytext());
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

    ( "azul" | "rojo" | "verde" | "celeste" | "negro" | "morado" | "anaranjado" | "rosado" | "cafe" | "amarillo" | "gris" ) {
        return new Symbol(Simbolo.color,yytext());
    }


    ( "rellena" | "contorno" ) {
        return new Symbol(Simbolo.tipo,yytext());
    }

    ( "S" | "M" | "L" ) {
        return new Symbol(Simbolo.tamano,yytext());
    }

    (" " | \r | \n | \t | \f)+ {}
    

    .   {   
        error(yytext());      

    }

}