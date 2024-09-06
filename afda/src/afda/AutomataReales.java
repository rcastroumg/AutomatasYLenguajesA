/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package afda;

/**
 *
 * @author Carlos
 */
public class AutomataReales {
    
    public boolean parsear(String entrada){
        boolean aceptar = false;
        char estado = 'A';
        
        char[] simbolos = entrada.toCharArray();
        
        for(char simbolo : simbolos){
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
                    }else if(simbolo == '.'){
                        estado = 'C';
                        aceptar = false;
                    }else{
                        return false;
                    }                    
                }
                case 'C' ->{
                    if(Character.isDigit(simbolo)){
                        estado = 'D';
                        aceptar = true;
                    }else{
                        return false;
                    }                    
                }
                case 'D' ->{
                    if(Character.isDigit(simbolo)){
                        estado = 'D';
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
