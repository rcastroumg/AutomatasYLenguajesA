/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package afda;

/**
 *
 * @author Carlos
 */
public class AutomataBinarioPar {
    
    public boolean parsear(String entrada){
        boolean aceptar = false;
        char estado = 'A';
        
        char[] simbolos = entrada.toCharArray();
        
        for(char simbolo: simbolos){
            switch(estado){
                case 'A'->{
                    switch(simbolo){
                        case '1'->{
                            estado = 'A';
                            aceptar = false;
                        }
                        case '0'->{
                            estado = 'B';
                            aceptar = true;
                        }
                        default->{
                            return false;
                        }
                    }
                }
                case 'B'->{
                    switch(simbolo){
                        case '1'->{
                            aceptar = false;
                            estado = 'A';
                        }
                        case '0'->{
                            estado = 'B';
                            aceptar = true;
                        }
                        default->{
                            return false;
                        }
                    }
                }
            }
        }
        
        return aceptar;
    }
    
}
