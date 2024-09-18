/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analizador.lexico;

import java.util.*;
import java.util.regex.*;

/**
 *
 * @author roberto
 */
public class AnalisisLexico_v3 {
    
    LinkedList<Simbolo> tablaLexico = new LinkedList<>();
    LinkedList<String> errorLexico = new LinkedList<>();
    
    // Clase auxiliar para definir los tokens
    public static class TokenDefinition {
        public final String name;
        public final String pattern;
        public final String color;
        public final String html;

        public TokenDefinition(String name, String pattern, String color, String html) {
            this.name = name;
            this.pattern = pattern;
            this.color = color;
            this.html = html;
        }
    }

    // Definición de las expresiones regulares para los tokens
    private static final List<TokenDefinition> tokenDefinitions = Arrays.asList(
        new TokenDefinition("Reservado",    "\\b(As|Cadena|Caso|CasoElse|Como|Dim|Entero|Entonces|Escribir|FinFuncion|FinMetodo|FinSegun|FinSi|Funcion|Hacer|Hasta|Leer|Metodo|Mientras|Para|Segun|Si|Sigueinte|Sino)\\b","green",""), // Palabras reservadas
        new TokenDefinition("Numero",       "\\d+(\\.\\d*)?","orange",""),      // Números
        new TokenDefinition("Cadena",       "\"[^\"]*\"","orange",""),          // Cadenas
        new TokenDefinition("Identificador","[A-Za-z_][A-Za-z0-9_]*","blue",""),// Identificadores
        new TokenDefinition("Igualigual",   "==","red","=="),                   // Operador de comparación
        new TokenDefinition("Mayorigual",   ">=","red","&gt;="),                // Operador de comparación
        new TokenDefinition("Menorigual",   "<=","red","&lt;="),                // Operador de comparación
        new TokenDefinition("Igual",        "=","red","="),                     // Operador de asignación
        new TokenDefinition("Mayorque",     ">","Red","&gt;"),                  // Operador de comparación
        new TokenDefinition("Menorque",     "<","Red","&lt;"),                  // Operador de comparación
        new TokenDefinition("Suma",         "\\+","red","+"),                   // Operador de suma
        new TokenDefinition("Resta",        "-","red","-"),                     // Operador de resta
        new TokenDefinition("Multiplica",   "\\*","red","*"),                   // Operador de multiplicación
        new TokenDefinition("Divide",       "/","red","/"),                     // Operador de división
        new TokenDefinition("ParAbre",      "\\(","purple","("),                // Paréntesis izquierdo
        new TokenDefinition("ParCierra",    "\\)","purple",")"),                // Paréntesis derecho
        new TokenDefinition("DosPuntos",    ":","purple",":"),                  // Dos puntos
        new TokenDefinition("Coma",         ",","purple",":"),                  // Coma
        new TokenDefinition("COMMENT",      "\'.*","gray",":"),                 // Comentarios
        new TokenDefinition("SPACE",        "[ ]+","","&nbsp;"),                // Espacios en blanco
        new TokenDefinition("TAB",          "[\\t]+","","&nbsp;&nbsp;&nbsp;"),  // Tabulador
        new TokenDefinition("NEWLINE",      "\\n","","<br>"),                   // Salto de linea
        new TokenDefinition("MISMATCH",     ".","","")                          // Cualquier otro carácter
        
    );

    // Función principal del lexer
    public String lex(String input) {
        int lineNum = 1;
        int lineStart = 0;
        String html = "";
        Pattern tokenPattern = buildTokenPattern();

        Matcher matcher = tokenPattern.matcher(input);
        while (matcher.find()) {
            TokenDefinition tdef = getMatched(matcher);
            String tokenType = tdef.name;
            String tokenValue = matcher.group(tokenType);
            String htmlValue = tokenValue;
            int column = matcher.start() - lineStart;
            
            Simbolo s = new Simbolo();
            s.token = tokenType;
            s.lexema = tokenValue;
            s.expresionRegular = tdef.pattern;
            s.linea = lineNum;
            s.columna = column;

            if (tokenType.equals("SPACE") || tokenType.equals("TAB") || tokenType.equals("NEWLINE")) {
                html += tdef.html;
                if(tokenType.equals("NEWLINE")){
                    lineStart += column;
                    lineNum ++;
                }
                continue; // Ignorar espacios en blanco, tabulador y salto de linea
            }
            if(tokenType.equals("COMMENT")){
                html += "<font style=\"font-size:15; color:"+tdef.color+";\">" + htmlValue + "</font>";
                continue; // Ignorar comentarios
            }
            if(tokenType.equals("Reservado")){
                tokenType = tokenValue;
                s.token = tokenValue;
                s.expresionRegular = tokenValue;
            }
            if(tokenType.equals("Menorque") || tokenType.equals("Mayorque") || tokenType.equals("Menorigual") || tokenType.equals("Mayorigual")){
                htmlValue = tdef.html;
            }
            if (tokenType.equals("MISMATCH")) {
                String error = "Error lexico linea " + lineNum + ", columna: " + column + ", caracter no reconocido: "+ tokenValue;
                errorLexico.add(error);
                continue;
            }
            
            tablaLexico.add(s);
            html += "<font style=\"font-size:15; color:"+tdef.color+";\">" + htmlValue + "</font>";
        }

        return html;
    }

    // Función auxiliar para construir el patrón de todas las expresiones regulares
    private static Pattern buildTokenPattern() {
        StringBuilder patternBuilder = new StringBuilder();
        for (TokenDefinition def : tokenDefinitions) {
            patternBuilder.append(String.format("|(?<%s>%s)", def.name, def.pattern));
        }
        return Pattern.compile(patternBuilder.substring(1)); // Eliminar el primer "|"
    }
    
    private static TokenDefinition getMatched(Matcher matcher) {
        for (TokenDefinition def : tokenDefinitions) {
            if (matcher.group(def.name) != null) {
                return def;
            }
        }
        throw new IllegalStateException("No matching token found");
    }
    
}
