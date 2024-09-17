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
    
    // Clase Token para representar un token con su tipo y valor
    public static class Token {
        public final String type;
        public final String value;
        public final int line;
        public final int column;

        public Token(String type, String value, int line, int column) {
            this.type = type;
            this.value = value;
            this.line = line;
            this.column = column;
        }

        @Override
        public String toString() {
            return String.format("Token[type=%s, value=%s, line=%d, column=%d]", type, value, line, column);
        }
    }

    // Definición de las expresiones regulares para los tokens
    private static final List<TokenDefinition> tokenDefinitions = Arrays.asList(
        new TokenDefinition("Reservado",   "\\b(As|Cadena|Caso|CasoElse|Como|Dim|Entero|Entonces|Escribir|FinFuncion|FinMetodo|FinSegun|FinSi|Funcion|Hacer|Hasta|Leer|Metodo|Mientras|Para|Segun|Si|Sigueinte|Sino)\\b","green",""), // Palabras clave
        new TokenDefinition("Numero",    "\\d+(\\.\\d*)?","orange",""),    // Números
        new TokenDefinition("Identificador",        "[A-Za-z_][A-Za-z0-9_]*","blue",""), // Identificadores
        new TokenDefinition("Igualigual",    "==","red","=="),                // Operador de asignación
        new TokenDefinition("Igual",    "=","red","="),                // Operador de asignación
        new TokenDefinition("Menorque",    "<","Red","&lt;"),                // Operador de asignación
        new TokenDefinition("PLUS",      "\\+","red","+"),              // Operador de suma
        new TokenDefinition("MINUS",     "-","red","-"),                // Operador de resta
        new TokenDefinition("TIMES",     "\\*","red","*"),              // Operador de multiplicación
        new TokenDefinition("DIVIDE",    "/","red","/"),                // Operador de división
        new TokenDefinition("LPAREN",    "\\(","purple","("),              // Paréntesis izquierdo
        new TokenDefinition("RPAREN",    "\\)","purple",")"),              // Paréntesis derecho
        //new TokenDefinition("SKIP",      "[ \\t]+","",""),          // Espacios en blanco
        new TokenDefinition("SPACE",      "[ ]+","","&nbsp;"),          // Espacios en blanco
        new TokenDefinition("TAB",      "[\\t]+","","&nbsp;&nbsp;&nbsp;"),          // Espacios en blanco
        new TokenDefinition("NEWLINE",   "\\n","","<br>"),
        new TokenDefinition("MISMATCH",  ".","","")                // Cualquier otro carácter
        
    );

    // Función principal del lexer
    public static List<Token> lex(String input) {
        List<Token> tokens = new ArrayList<>();
        int lineNum = 1;
        int lineStart = 0;
        String html = "";
        Pattern tokenPattern = buildTokenPattern();

        Matcher matcher = tokenPattern.matcher(input);
        while (matcher.find()) {
            TokenDefinition tdef = getMatched(matcher);
            String tokenType = getMatchedGroup(matcher);
            String tokenValue = matcher.group(tokenType);
            int column = matcher.start() - lineStart;

            if (tokenType.equals("SPACE")) {
                html += tdef.html;
                continue; // Ignorar espacios en blanco
            }
            if (tokenType.equals("TAB")) {
                html += tdef.html;
                continue; // Ignorar espacios en blanco
            }
            if (tokenType.equals("NEWLINE")) {
                html += tdef.html;
                lineStart += column + 1;
                lineNum ++;
                continue; // Ignorar espacios en blanco
            }
            if(tokenType == "Reservado"){
                tokenType = tokenValue;
            }
            if (tokenType.equals("MISMATCH")) {
                //throw new RuntimeException("Unexpected character: " + tokenValue + " at line " + lineNum);
                System.out.println("Unexpected character: " + tokenValue + " at line " + lineNum + " and column " + column);
            }

            tokens.add(new Token(tokenType, tokenValue, lineNum, column));
            html += "<p>" + tokenValue + "</p>";
        }

        System.out.println(html);
        return tokens;
    }

    // Función auxiliar para construir el patrón de todas las expresiones regulares
    private static Pattern buildTokenPattern() {
        StringBuilder patternBuilder = new StringBuilder();
        for (TokenDefinition def : tokenDefinitions) {
            patternBuilder.append(String.format("|(?<%s>%s)", def.name, def.pattern));
        }
        return Pattern.compile(patternBuilder.substring(1)); // Eliminar el primer "|"
    }

    // Obtener el grupo que coincide con el token en el matcher
    private static String getMatchedGroup(Matcher matcher) {
        for (TokenDefinition def : tokenDefinitions) {
            if (matcher.group(def.name) != null) {
                return def.name;
            }
        }
        throw new IllegalStateException("No matching token found");
    }
    
    private static TokenDefinition getMatched(Matcher matcher) {
        for (TokenDefinition def : tokenDefinitions) {
            if (matcher.group(def.name) != null) {
                return def;
            }
        }
        throw new IllegalStateException("No matching token found");
    }

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

    // Ejemplo de uso
    public static void main(String[] args) {
        String code = "Dim a1 As Entero = 2\n" +
"Dim a2 As Entero = 5 + 3\n" +
"\"Hola\"\n" +
"Si a1==a2 Entonces\n" +
"\ta1 = a1+a2\n" +
"Sino\n" +
"\ta2 = a1+a2\n" +
"FinSi\n";
        List<Token> tokens = lex(code);

        for (Token token : tokens) {
            System.out.println(token);
        }
    }
    
}
