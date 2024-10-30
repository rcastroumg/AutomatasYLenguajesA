/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graficadoraa;

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
        
        myList.add(new Colores("rojo","#FF0000",java.awt.Color.black));
        myList.add(new Colores("azul","#0000FF",java.awt.Color.black));
        myList.add(new Colores("verde","#00FF00",java.awt.Color.black));
        myList.add(new Colores("celeste","#00FFDD",java.awt.Color.black));
        myList.add(new Colores("negro","#000000",java.awt.Color.black));
        myList.add(new Colores("morado","#700094",java.awt.Color.black));
        myList.add(new Colores("anaranjado","#EF5100",java.awt.Color.black));
        myList.add(new Colores("rosado","#EA89C9",java.awt.Color.black));
        myList.add(new Colores("cafe","#623307",java.awt.Color.black));
        myList.add(new Colores("amarillo","#FFD300",java.awt.Color.black));
        myList.add(new Colores("gris","#9B9B9B",java.awt.Color.black));
       
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
