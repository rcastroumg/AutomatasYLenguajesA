/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analizador.lexico;

/**
 *
 * @author roberto
 */
public class AllowedSimbols {
    public String simbolo;
    public String simboloHtml;
    public String expresionRegular;
    public String lexema;
    public String token;
    public String color;
    
    public AllowedSimbols(
        String simbolo,
        String expresionRegular,
        String lexema,
        String token,
        String simboloHtml,
        String color
    ){
        this.simbolo = simbolo;
        this.expresionRegular = expresionRegular;
        this.lexema = lexema;
        this.token = token;
        this.simboloHtml = simboloHtml;
        this.color = color;
    }
}
