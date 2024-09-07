/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package laboratorio6;

import java.util.Scanner;

/**
 *
 * @author roberto
 */
public class Laboratorio6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner consola = new Scanner(System.in);
        
        int opcion;
        String entrada;
        do{
            System.out.println("Escoger una opción:");
            System.out.println("1. ER: ((b|cc)(c|b))*c");
            System.out.println("2. Numeros pares");
            System.out.println("6. Salir");

            opcion = consola.nextInt();
            if(opcion>0 && opcion<3){
                System.out.println("Ingrese entrada:");
                entrada = consola.next();
                switch(opcion){
                    case 1 -> {
                        Automata1 automata = new Automata1();
                        System.out.println(automata.parseRegEx(entrada));
                    }
                    case 2 -> {
                        Automata2 automata = new Automata2();
                        System.out.println(automata.parseRegEx(entrada));
                    }
                    
                }
            }
        
        }while(opcion>0 && opcion<3);
    }
    
}
