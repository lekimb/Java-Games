/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saberyganar;

import java.util.Scanner;

/**
 *
 * @author mikel
 */
public class Jugador {
    String nombre;
    int puntos;
    
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
    }
    
    public void imprimirJugador() {
        System.out.println(nombre);
    }
    public void imprimirPuntos() {
        System.out.println(nombre + ": " + puntos);
    }
    
    public void sumarPunto(){
        puntos++;
    }
    
    public boolean responderPregunta(Pregunta pregunta) {
        // Mostrar enunciado
        System.out.println("(Respuesta: " + pregunta.respuesta + ")"); // Mostrar respuesta en fase de desarrollo
        System.out.println(pregunta.enunciado);
        // Leer respuesta
        String respuestaStr;
        Scanner sc = new Scanner(System.in);
        respuestaStr = sc.next();
        
        boolean correcta = false;
        // MATES
        if (pregunta.tipo.equals("mates")) {
            // Convertir a integer
            int respuestaInt = Integer.parseInt(respuestaStr);
            // Si es correcta sumar punto
            if (respuestaInt == pregunta.respuesta) {
                correcta = true;
            } 
        }
        
        // INGLÉS
        
        // LENGUA  
        
        return correcta;
    }
}
