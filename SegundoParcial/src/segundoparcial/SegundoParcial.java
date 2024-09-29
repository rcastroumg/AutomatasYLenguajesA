/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package segundoparcial;

import java.util.Scanner;

/**
 *
 * @author roberto
 */
public class SegundoParcial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner consola = new Scanner(System.in);
        System.out.println("Escriba una entrada para la ER: a*(ab)*a:");
        String entrada = consola.next();
        AutomataER automata = new AutomataER();
        System.out.println(automata.parsear(entrada));
    }
    
}
