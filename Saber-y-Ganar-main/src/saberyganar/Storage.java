/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saberyganar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mikel
 */
public class Storage {
    static String rutaRegistro = "registro.txt";
    static String rutaHistorico = "historico.txt";
    
    public static String leerArchivo(String ruta) throws FileNotFoundException {
        File f = new File(ruta);
        Scanner sc = new Scanner(f);
        
        String texto = "";
        while (sc.hasNext()) {
            texto += sc.nextLine();
            if (sc.hasNext()) {
                texto += "\n"; // Salto de línea
            }
        }
        sc.close();
        return texto;
    }
    
    public static String leerRegistro() throws FileNotFoundException {
        return leerArchivo(rutaRegistro);
    }
    public static String leerHistorico() throws FileNotFoundException {
        return leerArchivo(rutaHistorico);
    }
    
    public static void añadirLinea(String ruta, String linea) throws IOException {
        File f = new File(ruta);
        FileWriter writer = new FileWriter(f, true); // modo append
        writer.write(linea + "\n");
        writer.close();
    }
    
    public static void añadirJugadorRegistro(String nombre) throws IOException {
        // Validar que no exista ya un jugador con el mismo nombre
        añadirLinea(rutaRegistro, nombre);
    }
    public static void añadirPartidaHistorico(String partida) throws IOException {
        añadirLinea(rutaHistorico, partida);
    }
    
    public static boolean eliminarJugadorRegistro(String nombre) throws IOException {
        String registroStr = leerRegistro();
        String[] registroArr = registroStr.split("\n");
        String nuevoRegistro = "";
        boolean eliminado = false;
        
        for (String r : registroArr) {
            if (!r.equals(nombre)) {
                nuevoRegistro += r + "\n";
            } else {
                eliminado = true;
            }
        }
        modificarRegistro(nuevoRegistro);
        return eliminado;
    }
    public static void modificarRegistro(String nuevoRegistro) throws IOException {
        File f = new File(rutaRegistro);
        FileWriter writer = new FileWriter(f, false); // modo overwrite
        writer.write(nuevoRegistro);
        writer.close();
    }

}
