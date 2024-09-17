/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.lexico;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Roberto
 */
public class AnalisisLexico_v2 {

    LinkedList<Simbolo> tablaLexico = new LinkedList<>();
    LinkedList<String> errorLexico = new LinkedList<>();
    List<AllowedSimbols> allowSimbol;
    List<String> reservedWords;
    List<DynamicStates> estadosGenerados = new ArrayList<>();
    String fontSize = "11";

    public String anlizar(String entrada) {
        generatStates();
        allowSimbol = getSimbolList();
        
        String codigoHtml = "";
        Simbolo s = new Simbolo();
        char[] letras = entrada.toCharArray();
        String estado = "A";
        int columna = 0;
        int linea = 1;
        boolean validarCaracteres;
        for (char letra : letras) {
            columna++;
            validarCaracteres = false;
            
            DynamicStates fstateLetter;
            DynamicStates fstateA;
            if(isNumeric(estado)){
                fstateLetter = findStateByLetter(estado,String.valueOf(letra));
                fstateA = findStateByStateEnd(estado,"A");
                if(fstateLetter != null){
                    estado = fstateLetter.haciaEstado;
                    s.lexema += String.valueOf(letra);
                    continue;
                }
                else if(fstateA != null){
                    switch (letra) {
                        case ' ':
                            estado = "A";
                            codigoHtml += saveLexico(s,"reservada");
                            s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            continue;
                        case '\n':
                            estado = "A";
                            codigoHtml += saveLexico(s,"reservada");
                            s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            continue;
                        case '\t':
                            estado = "A";
                            codigoHtml += saveLexico(s,"reservada");
                            s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            continue;
                        default:
                            validarCaracteres = true;
                            break;
                    }
                    if(this.findSimbol(String.valueOf(letra)) != null){
                        estado = "A";
                        codigoHtml += saveLexico(s,"reservada");
                        AllowedSimbols SimbolCur = this.findSimbol(String.valueOf(letra));
                        codigoHtml += saveLexico(new Simbolo(),"simbolo",SimbolCur,columna,linea);
                        s = new Simbolo();
                        validarCaracteres = false;
                    }
                    if (validarCaracteres) {
                        if(isCharNumUnderscore(letra)){
                            estado = "E";
                            s.lexema += letra;
                            s.columna = columna;
                            s.linea = linea;
                        }
                        else {
                            estado = "A";
                            codigoHtml += saveLexico(s,"reservada");
                            s = new Simbolo();
                            String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                            errorLexico.add(error);
                        }
                    }
                    continue;
                }
                else {
                    if(letra == ' '){
                        estado = "A";
                        codigoHtml += saveLexico(s,"identificador");
                        s = new Simbolo();
                        codigoHtml += "&nbsp;";
                        continue;
                    }
                    else if(letra == '\n'){
                        estado = "A";
                        codigoHtml += saveLexico(s,"identificador");
                        s = new Simbolo();
                        columna = 0;
                        linea++;
                        codigoHtml += "<br>";
                        continue;
                    }
                    else if(letra == '\t'){
                        estado = "A";
                        codigoHtml += saveLexico(s,"identificador");
                        s = new Simbolo();
                        codigoHtml += "&nbsp;&nbsp;&nbsp;";
                        continue;
                    }
                    else {
                        validarCaracteres = true;
                    }
                    if(this.findSimbol(String.valueOf(letra)) != null){
                        estado = "A";
                        codigoHtml += saveLexico(s,"identificador");
                        AllowedSimbols SimbolCur = this.findSimbol(String.valueOf(letra));
                        codigoHtml += saveLexico(new Simbolo(),"simbolo",SimbolCur,columna,linea);
                        s = new Simbolo();
                        validarCaracteres = false;
                    }
                    if (validarCaracteres) {
                        if(isCharNumUnderscore(letra)){
                            estado = "E";
                            s.lexema += letra;
                            s.columna = columna;
                            s.linea = linea;
                        }
                        else {
                            estado = "A";
                            codigoHtml += saveLexico(s,"identificador");
                            s = new Simbolo();
                            String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                            errorLexico.add(error);
                        }
                    }
                    continue;
                }
            }
            
            
            switch (estado) {
                case "A":
                    switch (letra) {
                        case '\'':
                            estado = "M";
                            s.lexema = "'";
                            break;
                        case '\"':
                            estado = "N";
                            s.lexema = "\"";
                            s.columna = columna;
                            s.linea = linea;
                            break;
                        case ' ':
                            codigoHtml += "&nbsp;";
                            break;
                        case '\n':
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        case '\t':
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        default:
                            validarCaracteres = true;
                    }
                    fstateLetter = findStateByLetter(String.valueOf(estado),String.valueOf(letra));
                    if(fstateLetter != null){
                        estado = fstateLetter.haciaEstado;
                        s.lexema = String.valueOf(letra);
                        s.columna = columna;
                        s.linea = linea;
                        validarCaracteres = false;
                    }
                    if(this.findSimbol(String.valueOf(letra)) != null){
                        AllowedSimbols SimbolCur = this.findSimbol(String.valueOf(letra));
                        codigoHtml += saveLexico(new Simbolo(),"simbolo",SimbolCur,columna,linea);
                        s = new Simbolo();
                        validarCaracteres = false;
                    }
                    if (validarCaracteres) {
                        if ((letra >= 65 && letra <= 90) || (letra >= 97 && letra <= 122)) {
                            estado = "E";
                            s.lexema = letra + "";
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            if (letra >= 48 && letra <= 57) {
                                estado = "F";
                                s.lexema = letra + "";
                                s.columna = columna;
                                s.linea = linea;
                            } else {
                                String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                                errorLexico.add(error);
                            }
                        }
                    }
                    break;
                case "F":
                    switch (letra) {
                        case ' ':
                            estado = "A";
                            codigoHtml += saveLexico(s,"numero");
                            s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        case '\n':
                            estado = "A";
                            codigoHtml += saveLexico(s,"numero");
                            s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        case '\t':
                            estado = "A";
                            codigoHtml += saveLexico(s,"numero");
                            s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        default:
                            validarCaracteres = true;
                    }
                    if(this.findSimbol(String.valueOf(letra)) != null){
                        estado = "A";
                        codigoHtml += saveLexico(s,"numero");
                        s = new Simbolo();
                        AllowedSimbols SimbolCur = this.findSimbol(String.valueOf(letra));
                        codigoHtml += saveLexico(new Simbolo(),"simbolo",SimbolCur,columna,linea);
                        s = new Simbolo();
                        validarCaracteres = false;
                    }
                    if (validarCaracteres) {
                        /*if (letra >= 48 && letra <= 57) {
                            estado = "E";
                            s.lexema += letra;
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            estado = "A";
                            codigoHtml += saveLexico(s,"numero");
                            s = new Simbolo();
                            String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                            errorLexico.add(error);
                        }*/
                        if ((letra >= 65 && letra <= 90) || (letra >= 97 && letra <= 122)) {
                            estado = "E";
                            s.lexema += letra + "";
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            if (letra >= 48 && letra <= 57) {
                                estado = "F";
                                s.lexema += letra + "";
                                s.columna = columna;
                                s.linea = linea;
                            } else {
                                String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                                errorLexico.add(error);
                            }
                        }
                    }
                    break;
                case "E":
                    switch (letra) {
                        case ' ':
                            estado = "A";
                            codigoHtml += saveLexico(s,"identificador");
                            s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        case '\n':
                            estado = "A";
                            codigoHtml += saveLexico(s,"identificador");
                            s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        case '\t':
                            estado = "A";
                            codigoHtml += saveLexico(s,"identificador");
                            s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        default:
                            validarCaracteres = true;
                    }
                    if(this.findSimbol(String.valueOf(letra)) != null){
                        estado = "A";
                        codigoHtml += saveLexico(s,"identificador");
                        AllowedSimbols SimbolCur = this.findSimbol(String.valueOf(letra));
                        codigoHtml += saveLexico(new Simbolo(),"simbolo",SimbolCur,columna,linea);
                        s = new Simbolo();
                        validarCaracteres = false;
                    }
                    if (validarCaracteres) {
                        if(isCharNumUnderscore(letra)){
                            estado = "E";
                            s.lexema += letra;
                            s.columna = columna;
                            s.linea = linea;
                        }
                        else {
                            estado = "A";
                            codigoHtml += saveLexico(s,"identificador");
                            s = new Simbolo();
                            String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                            errorLexico.add(error);
                        }
                    }
                    break;
                case "M":
                    switch (letra) {
                        case '\n':
                            estado = "A";
                            codigoHtml += createHtml(s.lexema,"gray");
                            s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        default:
                            s.lexema += letra;
                    }
                    break;
                case "N":
                    switch (letra) {
                        case '\"':
                            estado = "A";
                            s.lexema += "\"";
                            codigoHtml += saveLexico(s,"cadena");
                            //codigoHtml += createHtml(s.lexema,"orange");
                            s = new Simbolo();
                            columna = 0;
                            linea++;
                            break;
                        default:
                            s.lexema += letra;
                    }
                    break;
            }
        }

        return codigoHtml;

    }
    
    private String createHtml(String _text,String _color){
        return "<font style='font-size:"+this.fontSize+"' color='"+_color+"'>" + _text + "</font>";
    }
    
    private List<AllowedSimbols> getSimbolList(){
        List<AllowedSimbols> myList = new ArrayList<>();
        
        myList.add(new AllowedSimbols("+","+","+","mas","+","red"));
        myList.add(new AllowedSimbols("-","-","-","menos","-","red"));
        myList.add(new AllowedSimbols("*","*","*","multiplica","*","red"));
        myList.add(new AllowedSimbols("/","/","/","divide","/","red"));
        myList.add(new AllowedSimbols("<","<","<","menor","&lt;","red"));
        myList.add(new AllowedSimbols(">",">",">","mayor","&gt;","red"));
        myList.add(new AllowedSimbols("=","=","=","igual","=","red"));
        myList.add(new AllowedSimbols(",",",",",","coma",",","purple"));
        myList.add(new AllowedSimbols(":",":",":","dos puntos",":","purple"));
        myList.add(new AllowedSimbols(")",")",")","par. abre",")","purple"));
        myList.add(new AllowedSimbols("(","(","(","par. cierra","(","purple"));
       
        return myList;
    }
    
    private AllowedSimbols findSimbol(String _simbol){
        AllowedSimbols find = allowSimbol.stream()
                .filter(simbol -> _simbol.equals(simbol.simbolo))
                .findAny()
                .orElse(null);
        return find;
    }

    private boolean isCharNumUnderscore(char _letra){
        boolean ret = false;
        if ((_letra >= 65 && _letra <= 90) || (_letra >= 97 && _letra <= 122) || (_letra >= 48 && _letra <= 57) || (_letra == '_')) {
            return true;
        }
        return ret;
    }
    
    private String saveLexico(Simbolo _s, String _type){
        String html = "";
        switch (_type){
            case "identificador":
                _s.token = "identificador";
                _s.expresionRegular = "L(L|D|_)*";
                html = createHtml(_s.lexema,"blue");
                tablaLexico.add(_s);
                break;
            case "numero":
                _s.token = "numero";
                _s.expresionRegular = "D+";
                html = createHtml(_s.lexema,"orange");
                tablaLexico.add(_s);
                break;
            case "cadena":
                _s.token = "cadena";
                _s.expresionRegular = "[.]+";
                html = createHtml(_s.lexema,"orange");
                tablaLexico.add(_s);
                break;
            default:
                _s.token = _s.lexema;
                _s.expresionRegular = _s.lexema;
                html = createHtml(_s.lexema,"green");
                tablaLexico.add(_s);
                break;
        }
        
        return html;
    }
    
    private String saveLexico(Simbolo _s, String _type, AllowedSimbols _simbolCur, int _columna, int _linea){
        String html;

        html = createHtml(_simbolCur.simboloHtml,_simbolCur.color);
        _s.lexema = _simbolCur.lexema;
        _s.expresionRegular = _simbolCur.expresionRegular;
        _s.token = _simbolCur.token;
        _s.columna = _columna;
        _s.linea = _linea;
        tablaLexico.add(_s);
        
        return html;
    }
    
    private DynamicStates findStateByLetter(String _state, String _letter){
        DynamicStates find;
        
        find = estadosGenerados.stream()
        .filter(estado -> {
            if(_letter.equals(estado.letra) && _state.equals(estado.estadoActual)){
                return true;
            }
            return false;
        })
        .findAny()
        .orElse(null);
        
        return find;
    }
    
    private DynamicStates findStateByStateEnd(String _state, String _stateEnd){
        DynamicStates find;
        
        find = estadosGenerados.stream()
        .filter(estado -> {
            if(_stateEnd.equals(estado.haciaEstado) && _state.equals(estado.estadoActual)){
                return true;
            }
            return false;
        })
        .findAny()
        .orElse(null);
        
        return find;
    }
    
    private void generatStates(){
        reservedWords = getReservedWords();
        List<DynamicStates> estados = new ArrayList<>();
        DynamicStates newState;
        int conteoEstados = 0;
        
        for(String word: reservedWords){
            String estadoFor = "A";
            char[] letters = word.toCharArray();
            for(char letter: letters){
                String letraFor = String.valueOf(letter);
                String estadoLetra = estadoFor;
                DynamicStates find = estados.stream()
                .filter(estado -> {
                    if(letraFor.equals(estado.letra) && estadoLetra.equals(estado.estadoActual)){
                        return true;
                    }
                    return false;
                })
                .findAny()
                .orElse(null);
                
                if(find != null){
                    estadoFor = find.haciaEstado;
                }
                else {
                    conteoEstados ++;
                    newState = new DynamicStates();
                    newState.estadoActual = estadoFor;
                    newState.letra = letraFor;
                    newState.haciaEstado = String.valueOf(conteoEstados);
                    estados.add(newState);
                    estadoFor = String.valueOf(conteoEstados);
                }
            }
            newState = new DynamicStates();
            newState.estadoActual = estadoFor;
            newState.letra = "";
            newState.haciaEstado = "A";
            estados.add(newState);
        }
        
        estadosGenerados = estados;
    }
    
    private List<String> getReservedWords(){
        List<String> words = new ArrayList<String>();
        
        words.add("As");
        words.add("Cadena");
        words.add("Caso");
        words.add("CasoElse");
        words.add("Como");
        words.add("Dim");        
        words.add("Entero");
        words.add("Entonces");
        words.add("Escribir");
        words.add("FinFuncion");
        words.add("FinMetodo");
        words.add("FinSegun");
        words.add("FinSi");
        words.add("Funcion");
        words.add("Hacer");
        words.add("Hasta");
        words.add("Leer");
        words.add("Metodo");
        words.add("Mientras");
        words.add("Para");
        words.add("Segun");
        words.add("Si");
        words.add("Siguiente");
        words.add("Sino");
        
        return words;
    }
    
    public static boolean isNumeric(String cadena) {
        boolean resultado;
        try{
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
        return resultado;
    }

}
