/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package afda;

/**
 *
 * @author Carlos
 */
public class AutomataVariable {
    
    public boolean parsear(String entrada){
        boolean aceptar = false;
        char estado = 'A';
        
        char[] simbolos = entrada.toCharArray();
        
        for(char simbolo: simbolos){
            switch(estado){
                case 'A'->{
                    if(Character.isLetter(simbolo)){
                        estado = 'B';
                        aceptar = true;
                    }
                    else if(Character.isDigit(simbolo)){
                        return false;
                    }else if(simbolo == '_'){
                        return false;
                    }else{
                        return false;
                    }
                }
                case 'B'->{
                    if(Character.isLetter(simbolo)){
                        estado = 'B';
                        aceptar = true;
                    }
                    else if(Character.isDigit(simbolo)){
                        estado = 'B';
                        aceptar = true;
                    }else if(simbolo == '_'){
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
