/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package afda;

/**
 *
 * @author Carlos
 */
public class AutomataEnteros {
    
    public boolean parsear(String entrada){
        boolean aceptar = false;
        char estado = 'A';
        
        char[] simbolos = entrada.toCharArray();
        
        for(char simbolo: simbolos){
            switch(estado){
                case 'A' ->{
                    if(Character.isDigit(simbolo) || simbolo == '-' || simbolo == '+'){
                        estado = 'B';
                        aceptar = true;
                    }else{
                        return false;
                    }                    
                }
                case 'B' ->{
                    if(Character.isDigit(simbolo)){
                        estado = 'B';
                        aceptar = true;
                    }else{
                        return false;
                    }                    
                }
            }
        }
        
        return aceptar;
    }
    
}
