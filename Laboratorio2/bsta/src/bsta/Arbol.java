/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bsta;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author roberto
 */
public class Arbol {
    private Nodo raiz;
    
    public void insertar(int valor){
        
        Nodo nuevo = new Nodo();
        nuevo.valor = valor;
        
        if(raiz==null){
            raiz = nuevo;
        }
        else{
            insertarNodo(nuevo,raiz);
        }        
    }
    
    private void insertarNodo(Nodo nuevo,Nodo actual){
        
        if(nuevo.valor>actual.valor){
            if(actual.hijoDerecha == null){
                actual.hijoDerecha = nuevo;
            }
            else{
                insertarNodo(nuevo,actual.hijoDerecha);
            }
        }
        else{
            if(actual.hijoIzquierda == null){
                actual.hijoIzquierda = nuevo;
            }
            else{
                insertarNodo(nuevo,actual.hijoIzquierda);
            }
        }        
    
    }
    
    public void inorden(){
        if(raiz!=null)
        inorden(raiz);
    }
    
    private void inorden(Nodo actual){
        if(actual.hijoIzquierda!=null){
            inorden(actual.hijoIzquierda);
        }
        System.out.println(actual.valor);
        if(actual.hijoDerecha!=null){
            inorden(actual.hijoDerecha);
        }
    }
    
    public void postorden(){
        if(raiz!=null)
        postorden(raiz);
    }
    
     private void postorden(Nodo actual){
        if(actual.hijoIzquierda!=null){
            postorden(actual.hijoIzquierda);
        }
        if(actual.hijoDerecha!=null){
            postorden(actual.hijoDerecha);
        }
        System.out.println(actual.valor);
    }
     
    public void preorden(){
        String res = "";
        if(raiz!=null){
            res = preorden(raiz,res);
            System.out.println(res);
        }
        
    }
    
    private String preorden(Nodo actual, String _cadena){
        System.out.println(actual.valor);
        _cadena += actual.valor;
        if(actual.hijoIzquierda!=null){
            _cadena = preorden(actual.hijoIzquierda,_cadena+",");
        }
        if(actual.hijoDerecha!=null){
            _cadena = preorden(actual.hijoDerecha,_cadena+",");
        }
        return _cadena;
    }
    
    public void drawGraphviz(){
        try {
            createFile("Bsta.dot",graphviz());

            ProcessBuilder pb;
            pb = new ProcessBuilder("/usr/bin/dot","-Tpng","-o","Bsta.png","Bsta.dot");

            pb.redirectErrorStream(true);
            pb.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void createFile(String _ruta, String _contenido){
        FileWriter fw = null;
        PrintWriter pw = null;
        
        try {
            fw = new FileWriter(_ruta);
            pw = new PrintWriter(fw);
            pw.write(_contenido);
            pw.close();
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if(pw != null)
                pw.close();
        }
    }
    
    private String graphviz(){
        String res = "";
        if(raiz!=null){
            res += "digraph BST{\n"
                    + " node[shape=circle]\n"
                    + " node[style=filled]\n"
                    + " node[fillcolor=\"#ECD2A2\"]\n";
            res = graphviz(raiz,res);
            res += "}";
            //System.out.println(res);
        }
        return res;
    }
    
    private String graphviz(Nodo actual, String _cadena){
        //System.out.println(actual.valor);
        //_cadena += actual.valor;
        if(actual.hijoIzquierda!=null){
            _cadena += actual.valor + "->" + actual.hijoIzquierda.valor + ";\n";
            _cadena = graphviz(actual.hijoIzquierda,_cadena);
        }
        if(actual.hijoDerecha!=null){
            _cadena += actual.valor + "->" + actual.hijoDerecha.valor + ";\n";
            _cadena = graphviz(actual.hijoDerecha,_cadena);
        }
        return _cadena;
    }
}
