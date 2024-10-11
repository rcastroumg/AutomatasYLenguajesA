/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package binariopara;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Scanner;



/**
 *
 * @author roberto
 */
public class BinarioParA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner consola = new Scanner(System.in);
        System.out.println("Inrese un numero binario par: ");
        String entrada = consola.next();
        
        AnalisisLexico lexico = new AnalisisLexico(new BufferedReader(new StringReader(entrada)));
        
        AnalisisSintactico sintactico = new AnalisisSintactico(lexico);
        
        try {
            sintactico.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
