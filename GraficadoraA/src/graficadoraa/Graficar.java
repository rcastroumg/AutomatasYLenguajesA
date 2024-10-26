/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graficadoraa;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author carlos
 */
public class Graficar {
    
    public static void graficarCuadrado(String xs, String ys, String ls, String color, String tipo){
        int x = Integer.parseInt(xs);
        int y = Integer.parseInt(ys);
        int l = Integer.parseInt(ls);
        
        Graphics g = Interfaz.jPanel1.getGraphics();
        
        if("rojo".equals(color)){
            g.setColor(Color.red);
        }
        else{
            g.setColor(Color.blue);
        }
        
        if("rellena".equals(tipo)){
            g.fillRect(x, y, l, l);
        }
        else{
            g.drawRect(x, y, l, l);
        }
        
        
    }
    
}
