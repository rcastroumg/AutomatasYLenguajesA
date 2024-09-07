/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio6;

/**
 *
 * @author roberto
 */
public class Automata2 {
    public  boolean parseRegEx(String _regex){
        boolean ret = false;
        char state = 'A';
        
        char[] simbols = _regex.toCharArray();
        
        for(char simbol : simbols){
            switch(state){
                case 'A' -> {
                    switch(simbol){
                        case '1','3','5','7','9' ->{
                            ret = false;
                        }
                        case '0','2','4','6','8'->{
                            state = 'B';
                            ret = true;
                        }
                        default ->{
                            return false;
                        }
                    }
                }
                case 'B' -> {
                    switch(simbol){
                        case '1','3','5','7','9' ->{
                            state = 'A';
                            ret = false;
                        }
                        case '0','2','4','6','8'->{
                            ret = true;
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
