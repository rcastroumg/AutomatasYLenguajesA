/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package segundoparcial;

/**
 *
 * @author roberto
 */
public class AutomataER {
    public boolean parsear(String entrada){
        boolean aceptar = false;
        char estado = 'A';
        
        char[] simbolos = entrada.toCharArray();
        
        for(char simbolo : simbolos){
            switch(estado){
                case 'A' -> {
                    switch(simbolo){
                        case 'a' ->{
                            estado = 'B';
                            aceptar = true;
                        }
                        default ->{
                            return false;
                        }
                    }
                }
                case 'B' -> {
                    switch(simbolo){
                        case 'a' ->{
                            estado = 'B';
                            aceptar = true;
                        }
                        case 'b'->{
                            estado = 'C';
                            aceptar = false;
                        }
                        default ->{
                            return false;
                        }
                    }
                }
                case 'C' -> {
                    switch(simbolo){
                        case 'a' ->{
                            estado = 'D';
                            aceptar = true;
                        }
                        default ->{
                            return false;
                        }
                    }
                    
                }
                case 'D' -> {
                    switch(simbolo){
                        case 'b' ->{
                            estado = 'C';
                            aceptar = false;
                        }
                        default ->{
                            return false;
                        }
                    }
                    
                }
            }
        }
        
        return aceptar;
    }
}
