/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package afd;

import java.util.Scanner;

/**
 *
 * @author roberto
 */
public class AFD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner consola = new Scanner(System.in);
        System.out.println("Escriba una entrada para la ER: a*(ab|b)*:");
        String entrada = consola.next();
        AutomataER automata = new AutomataER();
        System.out.println(automata.parsear(entrada));
    }
    
}
