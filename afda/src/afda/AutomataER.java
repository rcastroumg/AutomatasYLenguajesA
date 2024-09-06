/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package afda;

/**
 *
 * @author Carlos
 */
public class AutomataER {
    
    public boolean parsear(String entrada){
        boolean aceptar = true;
        char estado = 'A';
        
        char[] simbolos = entrada.toCharArray();
        
        for(char simbolo : simbolos){
            switch(estado){
                case 'A' -> {
                    switch(simbolo){
                        case 'a' ->{
                            estado = 'A';
                            aceptar = true;
                        }
                        case 'b'->{
                            estado = 'C';
                            aceptar = true;
                        }
                        default ->{
                            return false;
                        }
                    }
                }
                case 'C' -> {
                    switch(simbolo){
                        case 'a' ->{
                            estado = 'E';
                            aceptar = false;
                        }
                        case 'b'->{
                            estado = 'C';
                            aceptar = true;
                        }
                        default ->{
                            return false;
                        }
                    }
                }
                case 'E' -> {
                    switch(simbolo){
                        case 'a' ->{
                            return false;
                        }
                        case 'b'->{
                            estado = 'C';
                            aceptar = true;
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
