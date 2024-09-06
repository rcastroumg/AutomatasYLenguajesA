/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bsta;

/**
 *
 * @author roberto
 */
public class Bsta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Arbol miArbol = new Arbol();
        
        miArbol.insertar(7);
        miArbol.insertar(4);
        miArbol.insertar(12);
        miArbol.insertar(1);
        miArbol.insertar(2);
        miArbol.insertar(6);
        miArbol.insertar(5);
        miArbol.insertar(100);
        miArbol.insertar(11);
        miArbol.insertar(14);
        
        //miArbol.inorden();
        
        //miArbol.postorden();
        
        miArbol.preorden();
        
        miArbol.drawGraphviz();
    }
    
}
