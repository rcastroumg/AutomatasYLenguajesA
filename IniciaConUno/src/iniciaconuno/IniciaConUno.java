/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package iniciaconuno;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Scanner;

/**
 *
 * @author roberto
 */
public class IniciaConUno {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner consola = new Scanner(System.in);
        System.out.println("Inrese un numero que inicie en uno: ");
        String entrada = consola.next();
        
        AnalisisLexico lexico = new AnalisisLexico(new BufferedReader(new StringReader(entrada)));
        
        AnalisisSintactico sintactico = new AnalisisSintactico(lexico);
        
        try {
            sintactico.parse();
            graficar(sintactico.codigo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void graficar(String codigo) {
        String texto = """
                       digraph G{
                       """;
        texto += codigo + "\n}";
        try {
            escribirArchivo("archivo.dot",texto);
            ProcessBuilder proceso= new ProcessBuilder("dot","-Tpng","-o","arbol.png","archivo.dot");
            proceso.redirectErrorStream(true);
            proceso.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void escribirArchivo(String ruta, String texto) {
        FileWriter fw;
        PrintWriter pw;
        
        try {
            fw = new FileWriter(ruta);
            pw = new PrintWriter(fw);
            pw.write(texto);
            pw.close();
            fw.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
