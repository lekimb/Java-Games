package wordle;

import java.util.ArrayList;

/**
 *
 * @author mikel
 */
public class Vista {
    
    public static void pintarPalabras(ArrayList<String> palabras, String solucion) {
        // Pintar palabras
        for (String palabra : palabras) {
            colorearPalabra(palabra, solucion);
            System.out.println(); // Salto de línea
        }
    }
    
    // ALGORITMO: 
    // 1. Aparece y está bien colocada? -> VERDE
    // 2. Aparece? 
    // 2.1 Está repetida?
    // 2.1.1 La repetición está bien colocada? -> GRIS
    // 2.1.2 La repetición está mal colocada?
    // 2.1.2.1 Es la primera aparición? -> AMARILLO
    // 2.1.2.2 Es la segunda aparición? -> GRIS
    // 2.2 No está repetida? -> AMARILLO
    // 3. No aparece? -> GRIS
    
    public static void colorearPalabra(String palabra, String solucion) {
        for (int i = 0; i < palabra.length(); i++) {
            String letra = "" + palabra.charAt(i); // Convert char to string
            if (palabra.charAt(i) == solucion.charAt(i)) { // Aparece en solución y bien colocada
                // GREEN
                System.out.print(ConsoleColors.GREEN + palabra.charAt(i) + ConsoleColors.RESET);
            } else if (solucion.matches(".*" + palabra.charAt(i) + ".*")) { // Aparece en solución pero está mal colocada
                if (palabra.matches(".*" + palabra.charAt(i) + ".*" + palabra.charAt(i) + ".*")) { // Aparece dos veces en palabra
                    int primeraAparicion = palabra.indexOf(letra);
                    int segundaAparicion = palabra.indexOf(letra, primeraAparicion + 1);
                    if (palabra.charAt(segundaAparicion) == solucion.charAt(segundaAparicion)) { // La repeticion está bien colocada
                        // GREY
                        System.out.print(palabra.charAt(i));
                    } else { // La repeticion está mal colocada
                        if (i == primeraAparicion) { // Es la primera aparición
                            // YELLOW
                            System.out.print(ConsoleColors.YELLOW + palabra.charAt(i) + ConsoleColors.RESET);
                        } else { // Es la segunda aparición
                            // GREY
                            System.out.print(palabra.charAt(i));
                        }
                    }
                } else { // Aparece una vez en palabra
                    // YELLOW
                    System.out.print(ConsoleColors.YELLOW + palabra.charAt(i) + ConsoleColors.RESET);
                }
            } else { // No aparece
                // GREY
                System.out.print(palabra.charAt(i));
            }
            System.out.print(" "); // Espacio entre letras
        }
    }  
}
