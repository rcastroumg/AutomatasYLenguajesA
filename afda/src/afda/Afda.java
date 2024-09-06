/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package afda;

import java.util.Scanner;

/**
 *
 * @author Carlos
 */
public class Afda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner consola = new Scanner(System.in);
        int opcion;
        String entrada;
        do{
            System.out.println("Escoger una opción:");
            System.out.println("1. ER: a*(ab|b)*");
            System.out.println("2. Binarios pares");
            System.out.println("3. Nombres variables");
            System.out.println("4. Enteros");
            System.out.println("5. Reales");
            System.out.println("6. Salir");

            opcion = consola.nextInt();
            if(opcion>0 && opcion<6){
                System.out.println("Ingrese entrada:");
                entrada = consola.next();
                switch(opcion){
                    case 1 -> {
                        AutomataER automata = new AutomataER();
                        System.out.println(automata.parsear(entrada));
                    }
                    case 2 -> {
                        AutomataBinarioPar automata = new AutomataBinarioPar();
                        System.out.println(automata.parsear(entrada));
                    }
                    case 3 -> {
                        AutomataVariable automata = new AutomataVariable();
                        System.out.println(automata.parsear(entrada));
                    }
                    case 4 -> {
                        AutomataEnteros automata = new AutomataEnteros();
                        System.out.println(automata.parsear(entrada));
                    }
                    case 5 -> {
                        AutomataReales automata = new AutomataReales();
                        System.out.println(automata.parsear(entrada));
                    }
                }
            }
        
        }while(opcion>0 && opcion<6);
        
        
    }
    
}
