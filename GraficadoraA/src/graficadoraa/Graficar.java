/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graficadoraa;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlos
 */
public class Graficar {
    
    public Graficar(){
        
        
    }
    
    
    
    public static void graficarCuadrado(String xs, String ys, String ls, String color, String tipo){
        int x = Integer.parseInt(xs);
        int y = Integer.parseInt(ys);
        int l = Integer.parseInt(ls);
        
        Graphics g = Interfaz.jPanel1.getGraphics();
        
        ColoresLst c = new ColoresLst();
        Colores mycolor = c.findColor(color);
        if(mycolor != null){
            if(!"".equals(mycolor.colorHex)){
                g.setColor(Color.decode(mycolor.colorHex));
            }
            else{
                g.setColor(mycolor.colorName);
            }
        }
        else{
            g.setColor(Color.black);
        }
        
        if("rellena".equals(tipo)){
            g.fillRect(x, y, l, l);
        }
        else{
            g.drawRect(x, y, l, l);
        }
        
        
    }
    
    public static void graficarLinea(String x1s, String y1s,String x2s, String y2s, String color) {
        int x1 = Integer.parseInt(x1s);
        int y1 = Integer.parseInt(y1s);
        int x2 = Integer.parseInt(x2s);
        int y2 = Integer.parseInt(y2s);
        
        Graphics g = Interfaz.jPanel1.getGraphics();
        
        ColoresLst c = new ColoresLst();
        Colores mycolor = c.findColor(color);
        if(mycolor != null){
            if(!"".equals(mycolor.colorHex)){
                g.setColor(Color.decode(mycolor.colorHex));
            }
            else{
                g.setColor(mycolor.colorName);
            }
        }
        else{
            g.setColor(Color.black);
        }
        
        g.drawLine(x1, y1, x2, y2);
        
    }
    
    public static void graficarRectangulo(String xs, String ys, String bs, String as, String color, String tipo) {
        int x = Integer.parseInt(xs);
        int y = Integer.parseInt(ys);
        int base = Integer.parseInt(bs);
        int altura = Integer.parseInt(as);
        
        Graphics g = Interfaz.jPanel1.getGraphics();
        
        ColoresLst c = new ColoresLst();
        Colores mycolor = c.findColor(color);
        if(mycolor != null){
            if(!"".equals(mycolor.colorHex)){
                g.setColor(Color.decode(mycolor.colorHex));
            }
            else{
                g.setColor(mycolor.colorName);
            }
        }
        else{
            g.setColor(Color.black);
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
        
        Graphics g = Interfaz.jPanel1.getGraphics();
        
        ColoresLst c = new ColoresLst();
        Colores mycolor = c.findColor(color);
        if(mycolor != null){
            if(!"".equals(mycolor.colorHex)){
                g.setColor(Color.decode(mycolor.colorHex));
            }
            else{
                g.setColor(mycolor.colorName);
            }
        }
        else{
            g.setColor(Color.black);
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
        
        Graphics g = Interfaz.jPanel1.getGraphics();
        
        ColoresLst c = new ColoresLst();
        Colores mycolor = c.findColor(color);
        if(mycolor != null){
            if(!"".equals(mycolor.colorHex)){
                g.setColor(Color.decode(mycolor.colorHex));
            }
            else{
                g.setColor(mycolor.colorName);
            }
        }
        else{
            g.setColor(Color.black);
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
        
        Graphics g = Interfaz.jPanel1.getGraphics();
        
        ColoresLst c = new ColoresLst();
        Colores mycolor = c.findColor(color);
        if(mycolor != null){
            if(!"".equals(mycolor.colorHex)){
                g.setColor(Color.decode(mycolor.colorHex));
            }
            else{
                g.setColor(mycolor.colorName);
            }
        }
        else{
            g.setColor(Color.black);
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
    
    
    public static void graficarEstrella(String xs, String ys, String pct, String color, String tipo) {
        
        int x = Integer.parseInt(xs);
        int y = Integer.parseInt(ys);
        int porcentaje = Integer.parseInt(pct);
        
        Graphics g = Interfaz.jPanel1.getGraphics();
        int i,n,x1,x2,y1,y2;
		
	double a=180.0,radio;
        x1=x;
        y1=y;
        n=5;			/*La variable n es el numero de muchos piquitos*/
        radio=(porcentaje*200)/100;
        y1=y1+(int)radio;

        ColoresLst c = new ColoresLst();
        Colores mycolor = c.findColor(color);
        if(mycolor != null){
            if(!"".equals(mycolor.colorHex)){
                g.setColor(Color.decode(mycolor.colorHex));
            }
            else{
                g.setColor(mycolor.colorName);
            }
        }
        else{
            g.setColor(Color.black);
        }
        
        Polygon p = new Polygon();
        for(i=1;i<=n;i++)
        {
                x2=(int)(x1-(radio*Math.cos(a)));
                y2=(int)(y1-(radio*Math.sin(a)));

                p.addPoint(x2,y2);

                a+=Math.PI/n;

                x2=(int)(x1-(.4*radio*Math.cos(a)));
                y2=(int)(y1-(.4*radio*Math.sin(a)));

                p.addPoint(x2,y2);

                a+=Math.PI/n;
        }
        
        if("rellena".equals(tipo)){
            g.fillPolygon(p);
            
        }
        else {
            g.drawPolygon(p);
        }

        //g.setColor(Color.black);
        //Font font = new Font("Serif", Font.BOLD, 20); 
        //g.setFont(font);         
        //g.drawString("No se que poner aqui", 110,25); 
        //g.setColor(Color.blue);
        //g.drawString("n="+n, 180,370);
            
    }
    
    public static void graficarPoligono(String coordinates, String color, String tipo) {
        String[] partsxy = coordinates.split(",");
        int[] X = new int[partsxy.length]; 
        int[] Y = new int[partsxy.length];
        
        for(int current=0;current<partsxy.length;current++) {
            String[] parts = partsxy[current].split(";");
            X[current] = Integer.parseInt(parts[0]);
            Y[current] = Integer.parseInt(parts[1]);
        }
        
        Graphics g = Interfaz.jPanel1.getGraphics();
        
        ColoresLst c = new ColoresLst();
        Colores mycolor = c.findColor(color);
        if(mycolor != null){
            if(!"".equals(mycolor.colorHex)){
                g.setColor(Color.decode(mycolor.colorHex));
            }
            else{
                g.setColor(mycolor.colorName);
            }
        }
        else{
            g.setColor(Color.black);
        }
        
        
        if("rellena".equals(tipo)){
            g.fillPolygon(X,Y,partsxy.length);
            
        }
        else {
            g.drawPolygon(X,Y,partsxy.length);
        }
    }
    
    public static void graficarEstrella2() {
        int max = 10;
        int midX = 0;
        int midY = 0;
        int radius[] = {90,40,90,40};
        int nPoints = 10;
        int[] X = new int[nPoints];
        int[] Y = new int[nPoints];
        
        Graphics g = Interfaz.jPanel1.getGraphics();

        for (double current=0.0; current<nPoints; current++)
        {
            int i = (int) current;
            double calc1 = Math.PI;
            double calc2 = calc1 * 2;
            double calc3 = calc2 / max;
            double calc4 = calc3 * current;
            double calc5 =  Math.cos(calc4);
            double calc52 = Math.sin(calc4);
            int calc6 = i % 4;
            int calc7 = radius[calc6];
            double calc8 = calc5 * calc7;
            double calc9 = calc52 * calc7;
            double x = calc8;
            double y = calc9;
            //double x = Math.cos(current*((2*Math.PI)/max))*radius[i % 4];
            //double y = Math.sin(current*((2*Math.PI)/max))*radius[i % 4];

            X[i] = (int) x+midX;
            Y[i] = (int) y+midY;
        }

        g.setColor(Color.red);
        g.fillPolygon(X, Y, nPoints);
    }
    
}

