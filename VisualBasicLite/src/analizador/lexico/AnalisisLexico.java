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
 * @author Carlos
 */
public class AnalisisLexico {

    LinkedList<Simbolo> tablaLexico = new LinkedList<>();
    LinkedList<String> errorLexico = new LinkedList<>();
    List<AllowedSimbols> allowSimbol;
    List<String> reservedWords;
    List<DynamicStates> estadosGenerados = new ArrayList<>();
    String fontSize = "8";

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
            
            DynamicStates fstate = findState(String.valueOf(estado),String.valueOf(letra),true);
            if(fstate != null && estado != "A"){
                if(fstate.haciaEstado == "A"){
                    if(letra == ' '){
                        estado = "A";
                        s.token = s.lexema;
                        s.expresionRegular = s.lexema;
                        codigoHtml += "<font size='"+this.fontSize+"' color='green'>" + s.lexema + "</font>";
                        tablaLexico.add(s);
                        s = new Simbolo();
                        codigoHtml += "&nbsp;";
                        continue;
                    }
                    else if(letra == '\n'){
                        estado = "A";
                        s.token = s.lexema;
                        s.expresionRegular = s.lexema;
                        codigoHtml += "<font size='"+this.fontSize+"' color='green'>" + s.lexema + "</font>";
                        tablaLexico.add(s);
                        s = new Simbolo();
                        columna = 0;
                        linea++;
                        codigoHtml += "<br>";
                        continue;
                    }
                    else if(letra == '\t'){
                        estado = "A";
                        s.token = s.lexema;
                        s.expresionRegular = s.lexema;
                        codigoHtml += "<font size='"+this.fontSize+"' color='green'>" + s.lexema + "</font>";
                        tablaLexico.add(s);
                        s = new Simbolo();
                        codigoHtml += "&nbsp;&nbsp;&nbsp;";
                        continue;
                    }
                    else {
                        validarCaracteres = true;
                    }
                    if(this.findSimbol(String.valueOf(letra)) != null){
                        estado = "A";
                        s.token = s.lexema;
                        s.expresionRegular = s.lexema;
                        codigoHtml += "<font size='"+this.fontSize+"' color='green'>" + s.lexema + "</font>";
                        tablaLexico.add(s);
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
                else{
                    if(String.valueOf(letra).equals(fstate.letra)){
                        estado = fstate.haciaEstado;
                        s.lexema += String.valueOf(letra);
                    }
                    else if(letra == ' '){
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
                        codigoHtml += saveLexico(s,"numero");
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
                        case 'i':
                            estado = "B";
                            s.lexema = "i";
                            s.columna = columna;
                            s.linea = linea;
                            break;
                        case 'e':
                            estado = "C";
                            s.lexema = "e";
                            s.columna = columna;
                            s.linea = linea;
                            break;
                        case 'v':
                            estado = "D";
                            s.lexema = "v";
                            s.columna = columna;
                            s.linea = linea;
                            break;
                        case '\'':
                            estado = "M";
                            s.lexema = "'";
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
                    fstate = findState(String.valueOf(estado),String.valueOf(letra),false);
                    if(fstate != null){
                        estado = fstate.haciaEstado;
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
                case "B":
                    switch (letra) {
                        case 'f':
                            estado = "G";
                            s.lexema = "if";
                            break;
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
                        s = new Simbolo();
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
                case "G":
                    switch (letra) {
                        case ' ':
                            estado = "A";
                            s.token = "if";
                            s.expresionRegular = "if";
                            codigoHtml += "<font size='"+this.fontSize+"' color='green'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        case '\n':
                            estado = "A";
                            s.token = "if";
                            s.expresionRegular = "if";
                            codigoHtml += "<font size='"+this.fontSize+"' color='green'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        case '\t':
                            estado = "A";
                            s.token = "if";
                            s.expresionRegular = "if";
                            codigoHtml += "<font size='"+this.fontSize+"' color='green'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        default:
                            validarCaracteres = true;
                    }
                    if(this.findSimbol(String.valueOf(letra)) != null){
                        estado = "A";
                        s.token = "if";
                        s.expresionRegular = "if";
                        codigoHtml += "<font size='"+this.fontSize+"' color='green'>" + s.lexema + "</font>";
                        tablaLexico.add(s);
                        s = new Simbolo();
                        AllowedSimbols SimbolCur = this.findSimbol(String.valueOf(letra));
                        codigoHtml += saveLexico(new Simbolo(),"simbolo",SimbolCur,columna,linea);
                        s = new Simbolo();
                        validarCaracteres = false;
                    }
                    if (validarCaracteres) {
                        if ((letra >= 65 && letra <= 90) || (letra >= 97 && letra <= 122)) {
                            estado = "E";
                            s.lexema += letra;
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            if (letra >= 48 && letra <= 57) {
                                estado = "E";
                                s.lexema += letra;
                                s.columna = columna;
                                s.linea = linea;
                            } else {
                                estado = "A";
                                s.token = "if";
                                s.expresionRegular = "if";
                                codigoHtml += "<font size='"+this.fontSize+"' color='green'>" + s.lexema + "</font>";
                                tablaLexico.add(s);
                                s = new Simbolo();
                                String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                                errorLexico.add(error);
                            }
                        }
                    }
                    break;
                case "C":
                    switch (letra) {
                        case 'l':
                            estado = "H";
                            s.lexema = "el";
                            break;
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
                        s = new Simbolo();
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
                case "H":
                    switch (letra) {
                        case 's':
                            estado = "I";
                            s.lexema = "els";
                            break;
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
                        s = new Simbolo();
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
                case "I":
                    switch (letra) {
                        case 'e':
                            estado = "J";
                            s.lexema = "else";
                            break;
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
                        s = new Simbolo();
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
                case "J":
                    switch (letra) {
                        case ' ':
                            estado = "A";
                            s.token = "else";
                            s.expresionRegular = "else";
                            codigoHtml += "<font size='"+this.fontSize+"' color='green'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        case '\n':
                            estado = "A";
                            s.token = "else";
                            s.expresionRegular = "else";
                            codigoHtml += "<font size='"+this.fontSize+"' color='green'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        case '\t':
                            estado = "A";
                            s.token = "else";
                            s.expresionRegular = "else";
                            codigoHtml += "<font size='"+this.fontSize+"' color='green'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        default:
                            validarCaracteres = true;
                    }
                    if(this.findSimbol(String.valueOf(letra)) != null){
                        estado = "A";
                        s.token = "else";
                        s.expresionRegular = "else";
                        codigoHtml += "<font size='"+this.fontSize+"' color='green'>" + s.lexema + "</font>";
                        tablaLexico.add(s);
                        s = new Simbolo();
                        AllowedSimbols SimbolCur = this.findSimbol(String.valueOf(letra));
                        codigoHtml += saveLexico(new Simbolo(),"simbolo",SimbolCur,columna,linea);
                        s = new Simbolo();
                        validarCaracteres = false;
                    }
                    if (validarCaracteres) {
                        if ((letra >= 65 && letra <= 90) || (letra >= 97 && letra <= 122)) {
                            estado = "E";
                            s.lexema += letra;
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            if (letra >= 48 && letra <= 57) {
                                estado = "E";
                                s.lexema += letra;
                                s.columna = columna;
                                s.linea = linea;
                            } else {
                                estado = "A";
                                s.token = "else";
                                s.expresionRegular = "else";
                                codigoHtml += "<font size='"+this.fontSize+"' color='green'>" + s.lexema + "</font>";
                                tablaLexico.add(s);
                                s = new Simbolo();
                                String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                                errorLexico.add(error);
                            }
                        }
                    }
                    break;
                case "D":
                    switch (letra) {
                        case 'a':
                            estado = "K";
                            s.lexema = "va";
                            break;
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
                        s = new Simbolo();
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
                case "K":
                    switch (letra) {
                        case 'r':
                            estado = "L";
                            s.lexema = "var";
                            break;
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
                        s = new Simbolo();
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
                case "L":
                    switch (letra) {
                        case ' ':
                            estado = "A";
                            s.token = "var";
                            s.expresionRegular = "var";
                            codigoHtml += "<font size='"+this.fontSize+"' color='green'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        case '\n':
                            estado = "A";
                            s.token = "var";
                            s.expresionRegular = "var";
                            codigoHtml += "<font size='"+this.fontSize+"' color='green'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        case '\t':
                            estado = "A";
                            s.token = "var";
                            s.expresionRegular = "var";
                            codigoHtml += "<font size='"+this.fontSize+"' color='green'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        default:
                            validarCaracteres = true;
                    }
                    if(this.findSimbol(String.valueOf(letra)) != null){
                        estado = "A";
                        s.token = "var";
                        s.expresionRegular = "var";
                        codigoHtml += "<font size='"+this.fontSize+"' color='green'>" + s.lexema + "</font>";
                        tablaLexico.add(s);
                        s = new Simbolo();
                        AllowedSimbols SimbolCur = this.findSimbol(String.valueOf(letra));
                        codigoHtml += saveLexico(new Simbolo(),"simbolo",SimbolCur,columna,linea);
                        s = new Simbolo();
                        validarCaracteres = false;
                    }
                    if (validarCaracteres) {
                        if ((letra >= 65 && letra <= 90) || (letra >= 97 && letra <= 122)) {
                            estado = "E";
                            s.lexema += letra;
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            if (letra >= 48 && letra <= 57) {
                                estado = "E";
                                s.lexema += letra;
                                s.columna = columna;
                                s.linea = linea;
                            } else {
                                estado = "A";
                                s.token = "var";
                                s.expresionRegular = "var";
                                codigoHtml += "<font size='"+this.fontSize+"' color='green'>" + s.lexema + "</font>";
                                tablaLexico.add(s);
                                s = new Simbolo();
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
                        if (letra >= 48 && letra <= 57) {
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
                            codigoHtml += "<font size='"+this.fontSize+"' color='gray'>" + s.lexema + "</font>";
                            s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        default:
                            s.lexema += letra;
                    }
                    break;
            }
            
        }

        return codigoHtml;

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
        
        //myList.add(new AllowedSimbols("{","{","{","par. cierra","{","coral"));
        //myList.add(new AllowedSimbols("}","}","}","par. cierra","}","coral"));
        //myList.add(new AllowedSimbols(";",";",";","par. cierra",";","coral"));
        
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
                html = "<font size='"+this.fontSize+"' color='blue'>" + _s.lexema + "</font>";
                tablaLexico.add(_s);
                break;
            case "numero":
                _s.token = "numero";
                _s.expresionRegular = "D+";
                html = "<font size='"+this.fontSize+"' color='orange'>" + _s.lexema + "</font>";
                tablaLexico.add(_s);
                break;
        }
        
        return html;
    }
    
    private String saveLexico(Simbolo _s, String _type, AllowedSimbols _simbolCur, int _columna, int _linea){
        String html = "";

        html = "<font size='"+this.fontSize+"' color='"+_simbolCur.color+"' >"+_simbolCur.simboloHtml+"</font>";
        _s.lexema = _simbolCur.lexema;
        _s.expresionRegular = _simbolCur.expresionRegular;
        _s.token = _simbolCur.token;
        _s.columna = _columna;
        _s.linea = _linea;
        tablaLexico.add(_s);
        
        return html;
    }
    
    private DynamicStates findState(String _state, String _letter, boolean _onlyState){
        DynamicStates find;
        if(_onlyState){
            find = estadosGenerados.stream()
            .filter(estado -> {
                if(_state.equals(estado.estadoActual)){
                    return true;
                }
                return false;
            })
            .findAny()
            .orElse(null);
        }
        else {
            find = estadosGenerados.stream()
            .filter(estado -> {
                if(_letter.equals(estado.letra) && _state.equals(estado.estadoActual)){
                    return true;
                }
                return false;
            })
            .findAny()
            .orElse(null);
        }
        
        return find;
    }
    
    
    private void generatStates(){
        reservedWords = getReservedWords();
        List<DynamicStates> estados = new ArrayList<>();
        DynamicStates newState;
        int conteoEstados = 0;
        //String letraFor = ""
;        
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
            //newState = estados.get(estados.size()-1);
            //estados.remove(estados.size()-1);
            //newState.haciaEstado = "A";
            //estados.add(newState);
            //conteoEstados--;
            
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

}
