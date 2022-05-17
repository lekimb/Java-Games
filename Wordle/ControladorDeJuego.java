package wordle;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mikel
 */
public class ControladorDeJuego {
    
    static public void jugar() {
        try {
            // Inicializar partida
            Partida partida = new Partida();
            // System.out.println("Solución: " + partida.getSolucion()); // Developement
            
            // Bucle de juego
            do {
                // Leer input jugador (ControladorDeJuego)
                String palabra = leerInput();
                System.out.println(); // Salto de línea

                // Jugar: introducir palabra (Partida)
                partida.introducirPalabra(palabra);

                // Pintar pantalla (Vista)
                ArrayList<String> palabrasJugadas = partida.getPalabrasJugadas();
                String solucion = partida.getSolucion();
                Vista.pintarPalabras(palabrasJugadas, solucion);

                // Comprobar fin de juego (Partida)
                if (partida.solucionAcertada()) {
                    break;
                }

            } while (partida.getPalabrasJugadas().size() < 6); // 6 rondas máximo
            
            // Comprobar ganar o perder
            if (partida.solucionAcertada()) {
                System.out.println("Acertaste la palabra!");
            } else {
                System.out.println("Has agotado los 6 intentos");
                System.out.println("La palabra era " + partida.getSolucion());
            }
        }
        catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }
    
    public static String leerInput() {
        System.out.print("> ");
        Scanner sc = new Scanner(System.in);
        String palabra = sc.next();
        while (!validarInput(palabra)) {
            System.out.print("> ");
            palabra = sc.next();
        }
        return palabra;
    }
    
    public static boolean validarInput(String input) {
        return input.length() == 5;
    }
    
}


