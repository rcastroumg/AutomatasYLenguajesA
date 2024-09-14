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

    public String anlizar(String entrada) {
        allowSimbol = getSimbolList();
        
        String codigoHtml = "";
        Simbolo s = new Simbolo();
        char[] letras = entrada.toCharArray();
        char estado = 'A';
        int columna = 0;
        int linea = 1;
        boolean validarCaracteres;
        for (char letra : letras) {
            columna++;
            validarCaracteres = false;
            switch (estado) {
                case 'A':
                    switch (letra) {
                        case 'i':
                            estado = 'B';
                            s.lexema = "i";
                            s.columna = columna;
                            s.linea = linea;
                            break;
                        case 'e':
                            estado = 'C';
                            s.lexema = "e";
                            s.columna = columna;
                            s.linea = linea;
                            break;
                        case 'v':
                            estado = 'D';
                            s.lexema = "v";
                            s.columna = columna;
                            s.linea = linea;
                            break;
                        case '\'':
                            estado = 'M';
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
                    if(this.findSimbol(String.valueOf(letra)) != null){
                        AllowedSimbols SimbolCur = this.findSimbol(String.valueOf(letra));
                        codigoHtml += "<font size='12' color='"+SimbolCur.color+"' >"+SimbolCur.simboloHtml+"</font>";
                        s.lexema = SimbolCur.lexema;
                        s.expresionRegular = SimbolCur.expresionRegular;
                        s.token = SimbolCur.token;
                        s.columna = columna;
                        s.linea = linea;
                        tablaLexico.add(s);
                        s = new Simbolo();
                        validarCaracteres = false;
                    }
                    if (validarCaracteres) {
                        if ((letra >= 65 && letra <= 90) || (letra >= 97 && letra <= 122)) {
                            estado = 'E';
                            s.lexema = letra + "";
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            if (letra >= 48 && letra <= 57) {
                                estado = 'F';
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
                case 'B':
                    switch (letra) {
                        case 'f':
                            estado = 'G';
                            s.lexema = "if";
                            break;
                        case '_':
                            estado = 'E';
                            s.lexema += "_";
                            break;
                        case ' ':
                            estado = 'A';
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        case '\n':
                            estado = 'A';
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        case '\t':
                            estado = 'A';
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        default:
                            validarCaracteres = true;
                    }
                    if(this.findSimbol(String.valueOf(letra)) != null){
                        estado = 'A';
                        s.token = "identificador";
                        s.expresionRegular = "L(L|D|_)*";
                        codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                        tablaLexico.add(s);
                        s = new Simbolo();
                        AllowedSimbols SimbolCur = this.findSimbol(String.valueOf(letra));
                        codigoHtml += "<font size='12' color='"+SimbolCur.color+"' >"+SimbolCur.simboloHtml+"</font>";
                        s.lexema = SimbolCur.lexema;
                        s.expresionRegular = SimbolCur.expresionRegular;
                        s.token = SimbolCur.token;
                        s.columna = columna;
                        s.linea = linea;
                        tablaLexico.add(s);
                        s = new Simbolo();
                        validarCaracteres = false;
                    }
                    if (validarCaracteres) {
                        if ((letra >= 65 && letra <= 90) || (letra >= 97 && letra <= 122)) {
                            estado = 'E';
                            s.lexema += letra;
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            if (letra >= 48 && letra <= 57) {
                                estado = 'E';
                                s.lexema += letra;
                                s.columna = columna;
                                s.linea = linea;
                            } else {
                                estado = 'A';
                                s.token = "identificador";
                                s.expresionRegular = "L(L|D|_)*";
                                codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                                tablaLexico.add(s);
                                s = new Simbolo();
                                String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                                errorLexico.add(error);
                            }
                        }

                    }
                    break;
                case 'G':
                    switch (letra) {
                        case '_':
                            estado = 'E';
                            s.lexema += "_";
                            break;
                        case ' ':
                            estado = 'A';
                            s.token = "if";
                            s.expresionRegular = "if";
                            codigoHtml += "<font size='12' color='green'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        case '\n':
                            estado = 'A';
                            s.token = "if";
                            s.expresionRegular = "if";
                            codigoHtml += "<font size='12' color='green'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        case '\t':
                            estado = 'A';
                            s.token = "if";
                            s.expresionRegular = "if";
                            codigoHtml += "<font size='12' color='green'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        default:
                            validarCaracteres = true;
                    }
                    if(this.findSimbol(String.valueOf(letra)) != null){
                        estado = 'A';
                        s.token = "if";
                        s.expresionRegular = "if";
                        codigoHtml += "<font size='12' color='green'>" + s.lexema + "</font>";
                        tablaLexico.add(s);
                        s = new Simbolo();
                        AllowedSimbols SimbolCur = this.findSimbol(String.valueOf(letra));
                        codigoHtml += "<font size='12' color='"+SimbolCur.color+"' >"+SimbolCur.simboloHtml+"</font>";
                        s.lexema = SimbolCur.lexema;
                        s.expresionRegular = SimbolCur.expresionRegular;
                        s.token = SimbolCur.token;
                        s.columna = columna;
                        s.linea = linea;
                        tablaLexico.add(s);
                        s = new Simbolo();
                        validarCaracteres = false;
                    }
                    if (validarCaracteres) {
                        if ((letra >= 65 && letra <= 90) || (letra >= 97 && letra <= 122)) {
                            estado = 'E';
                            s.lexema += letra;
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            if (letra >= 48 && letra <= 57) {
                                estado = 'E';
                                s.lexema += letra;
                                s.columna = columna;
                                s.linea = linea;
                            } else {
                                estado = 'A';
                                s.token = "if";
                                s.expresionRegular = "if";
                                codigoHtml += "<font size='12' color='green'>" + s.lexema + "</font>";
                                tablaLexico.add(s);
                                s = new Simbolo();
                                String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                                errorLexico.add(error);
                            }
                        }

                    }
                    break;
                case 'C':
                    switch (letra) {
                        case 'l':
                            estado = 'H';
                            s.lexema = "el";
                            break;
                        case '_':
                            estado = 'E';
                            s.lexema += "_";
                            break;
                        case ' ':
                            estado = 'A';
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        case '\n':
                            estado = 'A';
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        case '\t':
                            estado = 'A';
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        default:
                            validarCaracteres = true;
                    }
                    if(this.findSimbol(String.valueOf(letra)) != null){
                        estado = 'A';
                        s.token = "identificador";
                        s.expresionRegular = "L(L|D|_)*";
                        codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                        tablaLexico.add(s);
                        s = new Simbolo();
                        AllowedSimbols SimbolCur = this.findSimbol(String.valueOf(letra));
                        codigoHtml += "<font size='12' color='"+SimbolCur.color+"' >"+SimbolCur.simboloHtml+"</font>";
                        s.lexema = SimbolCur.lexema;
                        s.expresionRegular = SimbolCur.expresionRegular;
                        s.token = SimbolCur.token;
                        s.columna = columna;
                        s.linea = linea;
                        tablaLexico.add(s);
                        s = new Simbolo();
                        validarCaracteres = false;
                    }
                    if (validarCaracteres) {
                        if ((letra >= 65 && letra <= 90) || (letra >= 97 && letra <= 122)) {
                            estado = 'E';
                            s.lexema += letra;
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            if (letra >= 48 && letra <= 57) {
                                estado = 'E';
                                s.lexema += letra;
                                s.columna = columna;
                                s.linea = linea;
                            } else {
                                estado = 'A';
                                s.token = "identificador";
                                s.expresionRegular = "L(L|D|_)*";
                                codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                                tablaLexico.add(s);
                                s = new Simbolo();
                                String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                                errorLexico.add(error);
                            }
                        }

                    }
                    break;
                case 'H':
                    switch (letra) {
                        case 's':
                            estado = 'I';
                            s.lexema = "els";
                            break;
                        case '_':
                            estado = 'E';
                            s.lexema += "_";
                            break;
                        case ' ':
                            estado = 'A';
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        case '\n':
                            estado = 'A';
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        case '\t':
                            estado = 'A';
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        default:
                            validarCaracteres = true;
                    }
                    if(this.findSimbol(String.valueOf(letra)) != null){
                        estado = 'A';
                        s.token = "identificador";
                        s.expresionRegular = "L(L|D|_)*";
                        codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                        tablaLexico.add(s);
                        s = new Simbolo();
                        AllowedSimbols SimbolCur = this.findSimbol(String.valueOf(letra));
                        codigoHtml += "<font size='12' color='"+SimbolCur.color+"' >"+SimbolCur.simboloHtml+"</font>";
                        s.lexema = SimbolCur.lexema;
                        s.expresionRegular = SimbolCur.expresionRegular;
                        s.token = SimbolCur.token;
                        s.columna = columna;
                        s.linea = linea;
                        tablaLexico.add(s);
                        s = new Simbolo();
                        validarCaracteres = false;
                    }
                    if (validarCaracteres) {
                        if ((letra >= 65 && letra <= 90) || (letra >= 97 && letra <= 122)) {
                            estado = 'E';
                            s.lexema += letra;
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            if (letra >= 48 && letra <= 57) {
                                estado = 'E';
                                s.lexema += letra;
                                s.columna = columna;
                                s.linea = linea;
                            } else {
                                estado = 'A';
                                s.token = "identificador";
                                s.expresionRegular = "L(L|D|_)*";
                                codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                                tablaLexico.add(s);
                                s = new Simbolo();
                                String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                                errorLexico.add(error);
                            }
                        }

                    }
                    break;
                case 'I':
                    switch (letra) {
                        case 'e':
                            estado = 'J';
                            s.lexema = "else";
                            break;
                        case '_':
                            estado = 'E';
                            s.lexema += "_";
                            break;
                        case ' ':
                            estado = 'A';
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        case '\n':
                            estado = 'A';
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        case '\t':
                            estado = 'A';
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        default:
                            validarCaracteres = true;
                    }
                    if(this.findSimbol(String.valueOf(letra)) != null){
                        estado = 'A';
                        s.token = "identificador";
                        s.expresionRegular = "L(L|D|_)*";
                        codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                        tablaLexico.add(s);
                        s = new Simbolo();
                        AllowedSimbols SimbolCur = this.findSimbol(String.valueOf(letra));
                        codigoHtml += "<font size='12' color='"+SimbolCur.color+"' >"+SimbolCur.simboloHtml+"</font>";
                        s.lexema = SimbolCur.lexema;
                        s.expresionRegular = SimbolCur.expresionRegular;
                        s.token = SimbolCur.token;
                        s.columna = columna;
                        s.linea = linea;
                        tablaLexico.add(s);
                        s = new Simbolo();
                        validarCaracteres = false;
                    }
                    if (validarCaracteres) {
                        if ((letra >= 65 && letra <= 90) || (letra >= 97 && letra <= 122)) {
                            estado = 'E';
                            s.lexema += letra;
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            if (letra >= 48 && letra <= 57) {
                                estado = 'E';
                                s.lexema += letra;
                                s.columna = columna;
                                s.linea = linea;
                            } else {
                                estado = 'A';
                                s.token = "identificador";
                                s.expresionRegular = "L(L|D|_)*";
                                codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                                tablaLexico.add(s);
                                s = new Simbolo();
                                String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                                errorLexico.add(error);
                            }
                        }

                    }
                    break;
                case 'J':
                    switch (letra) {
                        case '_':
                            estado = 'E';
                            s.lexema += "_";
                            break;
                        case ' ':
                            estado = 'A';
                            s.token = "else";
                            s.expresionRegular = "else";
                            codigoHtml += "<font size='12' color='green'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        case '\n':
                            estado = 'A';
                            s.token = "else";
                            s.expresionRegular = "else";
                            codigoHtml += "<font size='12' color='green'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        case '\t':
                            estado = 'A';
                            s.token = "else";
                            s.expresionRegular = "else";
                            codigoHtml += "<font size='12' color='green'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        default:
                            validarCaracteres = true;
                    }
                    if(this.findSimbol(String.valueOf(letra)) != null){
                        estado = 'A';
                        s.token = "else";
                        s.expresionRegular = "else";
                        codigoHtml += "<font size='12' color='green'>" + s.lexema + "</font>";
                        tablaLexico.add(s);
                        s = new Simbolo();
                        AllowedSimbols SimbolCur = this.findSimbol(String.valueOf(letra));
                        codigoHtml += "<font size='12' color='"+SimbolCur.color+"' >"+SimbolCur.simboloHtml+"</font>";
                        s.lexema = SimbolCur.lexema;
                        s.expresionRegular = SimbolCur.expresionRegular;
                        s.token = SimbolCur.token;
                        s.columna = columna;
                        s.linea = linea;
                        tablaLexico.add(s);
                        s = new Simbolo();
                        validarCaracteres = false;
                    }
                    if (validarCaracteres) {
                        if ((letra >= 65 && letra <= 90) || (letra >= 97 && letra <= 122)) {
                            estado = 'E';
                            s.lexema += letra;
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            if (letra >= 48 && letra <= 57) {
                                estado = 'E';
                                s.lexema += letra;
                                s.columna = columna;
                                s.linea = linea;
                            } else {
                                estado = 'A';
                                s.token = "else";
                                s.expresionRegular = "else";
                                codigoHtml += "<font size='12' color='green'>" + s.lexema + "</font>";
                                tablaLexico.add(s);
                                s = new Simbolo();
                                String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                                errorLexico.add(error);
                            }
                        }

                    }
                    break;
                case 'D':
                    switch (letra) {
                        case 'a':
                            estado = 'K';
                            s.lexema = "va";
                            break;
                        case '_':
                            estado = 'E';
                            s.lexema += "_";
                            break;
                        case ' ':
                            estado = 'A';
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        case '\n':
                            estado = 'A';
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        case '\t':
                            estado = 'A';
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        default:
                            validarCaracteres = true;
                    }
                    if(this.findSimbol(String.valueOf(letra)) != null){
                        estado = 'A';
                        s.token = "identificador";
                        s.expresionRegular = "L(L|D|_)*";
                        codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                        tablaLexico.add(s);
                        s = new Simbolo();
                        AllowedSimbols SimbolCur = this.findSimbol(String.valueOf(letra));
                        codigoHtml += "<font size='12' color='"+SimbolCur.color+"' >"+SimbolCur.simboloHtml+"</font>";
                        s.lexema = SimbolCur.lexema;
                        s.expresionRegular = SimbolCur.expresionRegular;
                        s.token = SimbolCur.token;
                        s.columna = columna;
                        s.linea = linea;
                        tablaLexico.add(s);
                        s = new Simbolo();
                        validarCaracteres = false;
                    }
                    if (validarCaracteres) {
                        if ((letra >= 65 && letra <= 90) || (letra >= 97 && letra <= 122)) {
                            estado = 'E';
                            s.lexema += letra;
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            if (letra >= 48 && letra <= 57) {
                                estado = 'E';
                                s.lexema += letra;
                                s.columna = columna;
                                s.linea = linea;
                            } else {
                                estado = 'A';
                                s.token = "identificador";
                                s.expresionRegular = "L(L|D|_)*";
                                codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                                tablaLexico.add(s);
                                s = new Simbolo();
                                String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                                errorLexico.add(error);
                            }
                        }

                    }
                    break;
                case 'K':
                    switch (letra) {
                        case 'r':
                            estado = 'L';
                            s.lexema = "var";
                            break;
                        case '_':
                            estado = 'E';
                            s.lexema += "_";
                            break;
                        case ' ':
                            estado = 'A';
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        case '\n':
                            estado = 'A';
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        case '\t':
                            estado = 'A';
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        default:
                            validarCaracteres = true;
                    }
                    if(this.findSimbol(String.valueOf(letra)) != null){
                        estado = 'A';
                        s.token = "identificador";
                        s.expresionRegular = "L(L|D|_)*";
                        codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                        tablaLexico.add(s);
                        s = new Simbolo();
                        AllowedSimbols SimbolCur = this.findSimbol(String.valueOf(letra));
                        codigoHtml += "<font size='12' color='"+SimbolCur.color+"' >"+SimbolCur.simboloHtml+"</font>";
                        s.lexema = SimbolCur.lexema;
                        s.expresionRegular = SimbolCur.expresionRegular;
                        s.token = SimbolCur.token;
                        s.columna = columna;
                        s.linea = linea;
                        tablaLexico.add(s);
                        s = new Simbolo();
                        validarCaracteres = false;
                    }
                    if (validarCaracteres) {
                        if ((letra >= 65 && letra <= 90) || (letra >= 97 && letra <= 122)) {
                            estado = 'E';
                            s.lexema += letra;
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            if (letra >= 48 && letra <= 57) {
                                estado = 'E';
                                s.lexema += letra;
                                s.columna = columna;
                                s.linea = linea;
                            } else {
                                estado = 'A';
                                s.token = "identificador";
                                s.expresionRegular = "L(L|D|_)*";
                                codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                                tablaLexico.add(s);
                                s = new Simbolo();
                                String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                                errorLexico.add(error);
                            }
                        }

                    }
                    break;
                case 'L':
                    switch (letra) {
                        case '_':
                            estado = 'E';
                            s.lexema += "_";
                            break;
                        case ' ':
                            estado = 'A';
                            s.token = "var";
                            s.expresionRegular = "var";
                            codigoHtml += "<font size='12' color='green'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        case '\n':
                            estado = 'A';
                            s.token = "var";
                            s.expresionRegular = "var";
                            codigoHtml += "<font size='12' color='green'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        case '\t':
                            estado = 'A';
                            s.token = "var";
                            s.expresionRegular = "var";
                            codigoHtml += "<font size='12' color='green'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        default:
                            validarCaracteres = true;
                    }
                    if(this.findSimbol(String.valueOf(letra)) != null){
                        estado = 'A';
                        s.token = "var";
                        s.expresionRegular = "var";
                        codigoHtml += "<font size='12' color='green'>" + s.lexema + "</font>";
                        tablaLexico.add(s);
                        s = new Simbolo();
                        AllowedSimbols SimbolCur = this.findSimbol(String.valueOf(letra));
                        codigoHtml += "<font size='12' color='"+SimbolCur.color+"' >"+SimbolCur.simboloHtml+"</font>";
                        s.lexema = SimbolCur.lexema;
                        s.expresionRegular = SimbolCur.expresionRegular;
                        s.token = SimbolCur.token;
                        s.columna = columna;
                        s.linea = linea;
                        tablaLexico.add(s);
                        s = new Simbolo();
                        validarCaracteres = false;
                    }
                    if (validarCaracteres) {
                        if ((letra >= 65 && letra <= 90) || (letra >= 97 && letra <= 122)) {
                            estado = 'E';
                            s.lexema += letra;
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            if (letra >= 48 && letra <= 57) {
                                estado = 'E';
                                s.lexema += letra;
                                s.columna = columna;
                                s.linea = linea;
                            } else {
                                estado = 'A';
                                s.token = "var";
                                s.expresionRegular = "var";
                                codigoHtml += "<font size='12' color='green'>" + s.lexema + "</font>";
                                tablaLexico.add(s);
                                s = new Simbolo();
                                String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                                errorLexico.add(error);
                            }
                        }

                    }
                    break;
                case 'F':
                    switch (letra) {
                        case ' ':
                            estado = 'A';
                            s.token = "numero";
                            s.expresionRegular = "D+";
                            codigoHtml += "<font size='12' color='orange'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        case '\n':
                            estado = 'A';
                            s.token = "numero";
                            s.expresionRegular = "D+";
                            codigoHtml += "<font size='12' color='orange'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        case '\t':
                            estado = 'A';
                            s.token = "numero";
                            s.expresionRegular = "D+";
                            codigoHtml += "<font size='12' color='orange'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        default:
                            validarCaracteres = true;
                    }
                    if(this.findSimbol(String.valueOf(letra)) != null){
                        estado = 'A';
                        s.token = "numero";
                        s.expresionRegular = "D+";
                        codigoHtml += "<font size='12' color='orange'>" + s.lexema + "</font>";
                        tablaLexico.add(s);
                        s = new Simbolo();
                        AllowedSimbols SimbolCur = this.findSimbol(String.valueOf(letra));
                        codigoHtml += "<font size='12' color='"+SimbolCur.color+"' >"+SimbolCur.simboloHtml+"</font>";
                        s.lexema = SimbolCur.lexema;
                        s.expresionRegular = SimbolCur.expresionRegular;
                        s.token = SimbolCur.token;
                        s.columna = columna;
                        s.linea = linea;
                        tablaLexico.add(s);
                        s = new Simbolo();
                        validarCaracteres = false;
                    }
                    if (validarCaracteres) {
                        if (letra >= 48 && letra <= 57) {
                            estado = 'E';
                            s.lexema += letra;
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            estado = 'A';
                            s.token = "numero";
                            s.expresionRegular = "D+";
                            codigoHtml += "<font size='12' color='orange'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                            errorLexico.add(error);
                        }

                    }
                    break;
                case 'E':
                    switch (letra) {
                        case '_':
                            estado = 'E';
                            s.lexema += "_";
                            break;
                        case ' ':
                            estado = 'A';
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        case '\n':
                            estado = 'A';
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        case '\t':
                            estado = 'A';
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        default:
                            validarCaracteres = true;
                    }
                    if(this.findSimbol(String.valueOf(letra)) != null){
                        estado = 'A';
                        s.token = "identificador";
                        s.expresionRegular = "L(L|D|_)*";
                        codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                        tablaLexico.add(s);
                        s = new Simbolo();
                        AllowedSimbols SimbolCur = this.findSimbol(String.valueOf(letra));
                        codigoHtml += "<font size='12' color='"+SimbolCur.color+"' >"+SimbolCur.simboloHtml+"</font>";
                        s.lexema = SimbolCur.lexema;
                        s.expresionRegular = SimbolCur.expresionRegular;
                        s.token = SimbolCur.token;
                        s.columna = columna;
                        s.linea = linea;
                        tablaLexico.add(s);
                        s = new Simbolo();
                        validarCaracteres = false;
                    }
                    if (validarCaracteres) {
                        if ((letra >= 65 && letra <= 90) || (letra >= 97 && letra <= 122)) {
                            estado = 'E';
                            s.lexema += letra;
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            if (letra >= 48 && letra <= 57) {
                                estado = 'E';
                                s.lexema += letra;
                                s.columna = columna;
                                s.linea = linea;
                            } else {
                                estado = 'A';
                                s.token = "identificador";
                                s.expresionRegular = "L(L|D|_)*";
                                codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                                tablaLexico.add(s);
                                s = new Simbolo();
                                String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                                errorLexico.add(error);
                            }
                        }

                    }
                    break;
                case 'M':
                    switch (letra) {
                        case '\n':
                            estado = 'A';
                            codigoHtml += "<font size='12' color='gray'>" + s.lexema + "</font>";
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

}
