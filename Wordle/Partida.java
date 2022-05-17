package wordle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mikel
 */
public class Partida {
    private final String solucion;
    private ArrayList<String> palabrasJugadas = new ArrayList();
    
    public Partida() throws FileNotFoundException {
        // Fetch palabras de cinco letras
        ArrayList<String> palabrasCincoLetras = fetchPalabrasCincoLetras();
        // Seleccionar palabra aleatoria e inicializar solucion
        solucion = seleccionarPalabraAleatoria(palabrasCincoLetras);
    }
    
    // Getters
    public String getSolucion() {
        return solucion;
    }
    
    public ArrayList<String> getPalabrasJugadas() {
        return palabrasJugadas;
    }
    
    
    private ArrayList<String> fetchPalabrasCincoLetras() throws FileNotFoundException {
        // Inicializar File y Scanner
        File origen = new File("./palabrasMasComunes.txt");
        Scanner sc = new Scanner(origen);
        // Crear array con todas las palabras
        ArrayList<String> palabrasCincoLetras = new ArrayList<>();
        while (sc.hasNext()) {
            palabrasCincoLetras.add(sc.next());
        }
        return palabrasCincoLetras;
    }
    
    private String seleccionarPalabraAleatoria(ArrayList<String> palabrasCincoLetras) {
        int length = palabrasCincoLetras.size();
        int index = (int) (Math.random() * length);
        return palabrasCincoLetras.get(index).toUpperCase();
    }
    
    
    public void introducirPalabra(String palabra) {
        palabrasJugadas.add(palabra.toUpperCase());
    }
    
    public boolean solucionAcertada() {
        int ultimoIndice = palabrasJugadas.size() - 1;
        String ultimaPalabraJugada = palabrasJugadas.get(ultimoIndice);
        return ultimaPalabraJugada.equals(solucion);
    }
}
