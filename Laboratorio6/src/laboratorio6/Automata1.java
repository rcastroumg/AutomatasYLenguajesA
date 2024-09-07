/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio6;

/**
 *
 * @author roberto
 */
public class Automata1 {
    public  boolean parseRegEx(String _regex){
        boolean ret = false;
        char state = 'A';
        
        char[] simbols = _regex.toCharArray();
        
        for(char simbol : simbols){
            switch(state){
                case 'A' -> {
                    switch(simbol){
                        case 'b' ->{
                            state = 'B';
                            ret = true;
                        }
                        case 'c'->{
                            state = 'C';
                            ret = true;
                        }
                        default ->{
                            return false;
                        }
                    }
                }
                case 'B' -> {
                    switch(simbol){
                        case 'b', 'c' ->{
                            state = 'A';
                            ret = false;
                        }
                        default ->{
                            return false;
                        }
                    }
                }
                case 'C' -> {
                    switch(simbol){
                        case 'c' ->{
                            state = 'B';
                            ret = false;
                        }
                        default ->{
                            return false;
                        }
                    }
                }
            }
        }
        
        return ret;
    }
}
