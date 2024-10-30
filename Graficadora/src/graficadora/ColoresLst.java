/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graficadora;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author roberto
 */
public class ColoresLst {
    List<Colores> tabColores;
    
    public ColoresLst() {
        tabColores = getColorsList();
    }
    
    public List<Colores> getColorsList(){
        List<Colores> myList = new ArrayList<>();
        
        myList.add(new Colores("rojo","#FF0000"));
        myList.add(new Colores("azul","#0000FF"));
        myList.add(new Colores("verde","#00FF00"));
        myList.add(new Colores("celeste","#00FFDD"));
        myList.add(new Colores("negro","#000000"));
        myList.add(new Colores("morado","#700094"));
        myList.add(new Colores("anaranjado","#EF5100"));
        myList.add(new Colores("rosado","#ef0046"));
        myList.add(new Colores("cafe","#623307"));
        myList.add(new Colores("amarillo","#FFD300"));
        myList.add(new Colores("gris","#9B9B9B"));
       
        return myList;
    }
    
    public Colores findColor(String _id) {
        Colores find;
        
        find = tabColores.stream()
        .filter(color -> {
            if(_id.equals(color.id)){
                return true;
            }
            return false;
        })
        .findAny()
        .orElse(null);
        
        return find;
    }
}
