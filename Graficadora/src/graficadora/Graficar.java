/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graficadora;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author roberto
 */
public class Graficar {
    public static void graficarCuadrado(String xs, String ys, String ls, String color, String tipo) {
        int x = Integer.parseInt(xs);
        int y = Integer.parseInt(ys);
        int l = Integer.parseInt(ls);
        
        Graphics g = Interface.jPanel1.getGraphics();
        
        if("rojo".equals(color)){
            g.setColor(Color.red);
        }
        else {
            g.setColor(Color.blue);
        }
        
        if("rellena".equals(tipo)){
            g.fillRect(x, y, l, l);
            
        }
        else {
            g.drawRect(x, y, l, l);
        }
    }
    
    public static void graficarLinea(String x1s, String y1s,String x2s, String y2s, String color) {
        int x1 = Integer.parseInt(x1s);
        int y1 = Integer.parseInt(y1s);
        int x2 = Integer.parseInt(x2s);
        int y2 = Integer.parseInt(y2s);
        
        Graphics g = Interface.jPanel1.getGraphics();
        
        if("rojo".equals(color)){
            g.setColor(Color.red);
        }
        else {
            g.setColor(Color.blue);
        }
        
        g.drawLine(x1, y1, x2, y2);
        
    }
    
    public static void graficarRectangulo(String xs, String ys, String bs, String as, String color, String tipo) {
        int x = Integer.parseInt(xs);
        int y = Integer.parseInt(ys);
        int base = Integer.parseInt(bs);
        int altura = Integer.parseInt(as);
        
        Graphics g = Interface.jPanel1.getGraphics();
        
        if("rojo".equals(color)){
            g.setColor(Color.red);
        }
        else {
            g.setColor(Color.blue);
        }
        
        if("rellena".equals(tipo)){
            g.fillRect(x, y, base, altura);
            
        }
        else {
            g.drawRect(x, y, base, altura);
        }
    }
    
    public static void graficarCirculo(String xs, String ys, String radios, String color, String tipo) {
        int x = Integer.parseInt(xs);
        int y = Integer.parseInt(ys);
        int radio = Integer.parseInt(radios);
        
        Graphics g = Interface.jPanel1.getGraphics();
        
        if("rojo".equals(color)){
            g.setColor(Color.red);
        }
        else {
            g.setColor(Color.blue);
        }
        
        if("rellena".equals(tipo)){
            g.fillOval(x-radio, y-radio, radio*2, radio*2);
            
        }
        else {
            g.drawOval(x-radio, y-radio, radio*2, radio*2);
        }
    }
    
    public static void graficarOvalo(String xs, String ys, String ejexs, String ejeys, String color, String tipo) {
        int x = Integer.parseInt(xs);
        int y = Integer.parseInt(ys);
        int ejex = Integer.parseInt(ejexs);
        int ejey = Integer.parseInt(ejeys);
        
        Graphics g = Interface.jPanel1.getGraphics();
        
        if("rojo".equals(color)){
            g.setColor(Color.red);
        }
        else {
            g.setColor(Color.blue);
        }
        
        if("rellena".equals(tipo)){
            g.fillOval(x-(ejex/2), y-(ejey/2), ejex, ejey);
            
        }
        else {
            g.drawOval(x-(ejex/2), y-(ejey/2), ejex, ejey);
        }
    }
    
    public static void graficarTrianguloRectangulo(String xs, String ys, String bases, String alturas, String color, String tipo) {
        int x = Integer.parseInt(xs);
        int y = Integer.parseInt(ys);
        int base = Integer.parseInt(bases);
        int altura = Integer.parseInt(alturas);
        
        Graphics g = Interface.jPanel1.getGraphics();
        
        if("rojo".equals(color)){
            g.setColor(Color.red);
        }
        else {
            g.setColor(Color.blue);
        }
        
        // x coordinates of vertices 
        int xPoints[] = { x, x+base, x }; 
  
        // y coordinates of vertices 
        int yPoints[] = { y, y, y-altura }; 
  
        // number of vertices 
        int numberofpoints = 3; 
  
        // create a polygon with given x, y coordinates 
        java.awt.Polygon p = new java.awt.Polygon(xPoints, yPoints, numberofpoints); 
        
        if("rellena".equals(tipo)){
            g.fillPolygon(p);
            
        }
        else {
            g.drawPolygon(p);
        }
    }
    
    
    public static void graficarEstrella() {
        int max = 10;
        int midX = 200;
        int midY = 240;
        int radius[] = {90,40,90,40};
        int nPoints = 10;
        int[] X = new int[nPoints];
        int[] Y = new int[nPoints];
        
        Graphics g = Interface.jPanel1.getGraphics();

        for (double current=0.0; current<nPoints; current++)
        {
            int i = (int) current;
            double x = Math.cos(current*((2*Math.PI)/max))*radius[i % 4];
            double y = Math.sin(current*((2*Math.PI)/max))*radius[i % 4];

            X[i] = (int) x+midX;
            Y[i] = (int) y+midY;
        }

        g.setColor(Color.red);
        g.fillPolygon(X, Y, nPoints);
    }
}
