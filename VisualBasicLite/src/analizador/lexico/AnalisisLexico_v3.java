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
        new TokenDefinition("Cadena",       "\"[^\"]*\"","orange",""),
        new TokenDefinition("Identificador",        "[A-Za-z_][A-Za-z0-9_]*","blue",""), // Identificadores
        new TokenDefinition("Igualigual",    "==","red","=="),                // Operador de asignación
        new TokenDefinition("Mayorigual",    ">=","red","&gt;="),                // Operador de asignación
        new TokenDefinition("Menorigual",    "<=","red","&lt;="),                // Operador de asignación
        new TokenDefinition("Igual",    "=","red","="),                // Operador de asignación
        new TokenDefinition("Mayorque",    ">","Red","&gt;"),                // Operador de asignación
        new TokenDefinition("Menorque",    "<","Red","&lt;"),                // Operador de asignación
        new TokenDefinition("Suma",      "\\+","red","+"),              // Operador de suma
        new TokenDefinition("Resta",     "-","red","-"),                // Operador de resta
        new TokenDefinition("Multiplica",     "\\*","red","*"),              // Operador de multiplicación
        new TokenDefinition("Divide",    "/","red","/"),                // Operador de división
        new TokenDefinition("ParAbre",    "\\(","purple","("),              // Paréntesis izquierdo
        new TokenDefinition("ParCierra",    "\\)","purple",")"),              // Paréntesis derecho
        new TokenDefinition("DosPuntos",    ":","purple",":"),              // Paréntesis derecho
        new TokenDefinition("Coma",    ",","purple",":"),              // Paréntesis derecho
        //new TokenDefinition("SKIP",      "[ \\t]+","",""),          // Espacios en blanco
        new TokenDefinition("COMMENT",    "\'.*","gray",":"),              // Paréntesis derecho
        new TokenDefinition("SPACE",      "[ ]+","","&nbsp;"),          // Espacios en blanco
        new TokenDefinition("TAB",      "[\\t]+","","&nbsp;&nbsp;&nbsp;"),          // Tabulador
        new TokenDefinition("NEWLINE",   "\\n","","<br>"),
        new TokenDefinition("MISMATCH",  ".","","")                // Cualquier otro carácter
        
    );

    // Función principal del lexer
    public String lex(String input) {
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
                    lineStart += column + 1;
                    lineNum ++;
                }
                continue; // Ignorar espacios en blanco, tabulador y salto de linea
            }
            if(tokenType.equals("COMMENT")){
                html += "<font style=\"font-size:15; color:"+tdef.color+";\">" + htmlValue + "</font>";
                continue;
            }
            if(tokenType.equals("Reservado")){
                tokenType = tokenValue;
                s.token = tokenValue;
                s.expresionRegular = tokenValue;
            }
            if(tokenType.equals("Menorque")){
                htmlValue = tdef.html;
            }
            if (tokenType.equals("MISMATCH")) {
                //throw new RuntimeException("Unexpected character: " + tokenValue + " at line " + lineNum);
                //System.out.println("Unexpected character: " + tokenValue + " at line " + lineNum + " and column " + column);
                String error = "Error lexico linea " + lineNum + ", columna: " + column + ", caracter no reconocido: "+ tokenValue;
                errorLexico.add(error);
                continue;
            }

            tokens.add(new Token(tokenType, tokenValue, lineNum, column));
            
            tablaLexico.add(s);
            html += "<font style=\"font-size:15; color:"+tdef.color+";\">" + htmlValue + "</font>";
        }

        //System.out.println(html);
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
"Si a1<a2 Entonces\n" +
"\ta1 = a1+a2\n" +
"Sino\n" +
"\ta2 = a1+a2\n" +
"FinSi\n";
        
        /*AnalisisLexico_v3 analizador = new AnalisisLexico_v3();
        List<Token> tokens = analizador.lex(code);

        for (Token token : tokens) {
            System.out.println(token);
        }*/
    }
    
}
