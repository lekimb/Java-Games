/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package saberyganar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import javax.script.ScriptException;

/**
 *
 * @author mikel
 */
public class SaberYGanar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ScriptException, FileNotFoundException {

        menuPrincipal();
        
    }
    
    public static void menuPrincipal() throws FileNotFoundException, ScriptException {
        Scanner sc = new Scanner(System.in);
        String opcion;
        do {
            // Mostrar opciones
            System.out.println("1. Jugar partida");
            System.out.println("2. Ranking");
            System.out.println("3. Histórico");
            System.out.println("4. Jugadores");
            System.out.println("5. Salir");
            
            // Leer opción
            opcion = sc.next();

            // Switch 
            switch (opcion) {
                case "1":
                    jugarPartida();
                    break;
                case "2":
                    imprimirRanking();
                    break;
                case "3":
                    imprimirHistorico();
                    break;
                case "4":
                    menuJugadores();
                    break;
                case "5":
                    System.out.println("Gracias por jugar");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (!opcion.matches("5")); // Volver siempre al menú principal excepto para salir
    }
    
    public static ArrayList<Jugador> seleccionJugadores() throws FileNotFoundException, ScriptException {
        Scanner sc = new Scanner(System.in);
        String opcion;
        String jugadoresRegistradosStr = Storage.leerRegistro();
        String jugadoresPartidaStr = "";
        do {
            // Mostrar opciones (opción reset y volver)
            System.out.println("1. Jugar");
            System.out.println("2. Añadir jugador");
            System.out.println("3. Reset");     
            // Mostrar jugadores registrados disponibles
            imprimirTablaSeleccionJugadores(jugadoresRegistradosStr, jugadoresPartidaStr);

            opcion = sc.next();

            try {
                switch(opcion) {
                    case "1":
                        // Salir del bucle 
                        if (jugadoresPartidaStr.equals("")) {
                            throw new Exception();
                        }
                        break;
                    case "2":
                        // añadirJugador
                        jugadoresPartidaStr = añadirJugador(jugadoresPartidaStr);
                        break;
                    case "3":
                        jugadoresPartidaStr = "";
                        break;
                    default:
                        break;
                }
            }
            catch (Exception e){
                opcion = "0"; // Cualquier cosa excepto 1 valdría para seguir en el bucle 
            }
        } while(!opcion.matches("1")); // En caso de default volvería al menú principal
        
        // Instanciar Jugadores a partir de jugadoresPartida
        ArrayList<Jugador> jugadoresPartida = new ArrayList<>();
        for (String nombre : jugadoresPartidaStr.split("\n")) {
            Jugador j = new Jugador(nombre);
            jugadoresPartida.add(j);
        } 
        return jugadoresPartida;
    }
    
    public static String añadirJugador(String jugadoresPartida) throws FileNotFoundException {
        // 6 jugadores máximo
        String[] jugadoresPartidaArr = jugadoresPartida.split("\n");
        if (jugadoresPartidaArr.length == 6) {
            System.out.println("Máximo 6 jugadores!");
            return jugadoresPartida;
        }

        // Leer nombre jugador
        Scanner sc = new Scanner(System.in);
        System.out.print("Escribe el nombre de un jugador registrado: ");
        String nombre = sc.next();
        // Leer el registro para comparar
        String registroStr = Storage.leerRegistro();
        String[] registroArr = registroStr.split("\n");
        
        boolean añadir = true;
        // Comprobar que está registrado
        if (jugadorYaRegistrado(nombre)) {
            // Comprobar que no está ya añadido a la partida
            for (String jugadorPartida : jugadoresPartidaArr) {
                if (nombre.equals(jugadorPartida)) {
                    añadir = false;
                }
            }
            if (añadir) {
                jugadoresPartida += nombre + "\n";
            }
        }
        return jugadoresPartida;
    }
    
    public static void imprimirTablaSeleccionJugadores(String registro, String partida) {
        String[] registroArr = registro.split("\n");
        String[] partidaArr = partida.split("\n");
        // Calcular nombre más largo
        String nombreMasLargo = "";
        for (String jugador : registroArr) {
            if (jugador.length() > nombreMasLargo.length()) {
                nombreMasLargo = jugador;
            }
        }
        // Imprimir borde superior
        System.out.print("+ ");
        for (int i = 0; i < nombreMasLargo.length(); i++) {
            System.out.print("-");
        }
        System.out.print(" + ");
        for (int i = 0; i < nombreMasLargo.length(); i++) {
            System.out.print("-");
        }
        System.out.println(" +");
        // Imprimir títulos
        System.out.print("| REGISTRO");
        if ("REGISTRO".length() < nombreMasLargo.length()) {
            for (int i = 0; i < nombreMasLargo.length() - "REGISTRO".length(); i++) {
                System.out.print(" ");
            }
        }
        System.out.print(" | PARTIDA");
        if ("PARTIDA".length() < nombreMasLargo.length()) {
            for (int i = 0; i < nombreMasLargo.length() - "PARTIDA".length(); i++) {
                System.out.print(" ");
            }
        }
        System.out.println(" |");
        // Imprimir borde medio
        System.out.print("+ ");
        for (int i = 0; i < nombreMasLargo.length(); i++) {
            System.out.print("-");
        }
        System.out.print(" + ");
        for (int i = 0; i < nombreMasLargo.length(); i++) {
            System.out.print("-");
        }
        System.out.println(" +");
        
        // Calcular array más largo
        String[] arrayMasLargo;
        if (registroArr.length >= partidaArr.length) {
            arrayMasLargo = registroArr;
        } else {
            arrayMasLargo = partidaArr;
        }
        // Imprimir filas
        for (int i = 0; i < arrayMasLargo.length; i++) {
            try {
                System.out.print("| " + registroArr[i]);
                if (registroArr[i].length() < nombreMasLargo.length()) {
                    for (int j = 0; j < nombreMasLargo.length() - registroArr[i].length(); j++) {
                        System.out.print(" ");
                    }
                }
            }
            catch (Exception e) {
                System.out.print("| ");
                for (int j = 0; j < nombreMasLargo.length(); j++) {
                    System.out.print(" ");
                }
            }
            try {
                System.out.print(" | " + partidaArr[i]);
                if (partidaArr[i].length() < nombreMasLargo.length()) {
                    for (int j = 0; j < nombreMasLargo.length() - partidaArr[i].length(); j++) {
                        System.out.print(" ");
                    }
                }
                System.out.println(" |");
            }
            catch (Exception e) {
                System.out.print(" | ");
                for (int j = 0; j < nombreMasLargo.length(); j++) {
                    System.out.print(" ");
                }
                System.out.println(" |");
            }
        }
        // Imprimir borde in inferior
        System.out.print("+ ");
        for (int i = 0; i < nombreMasLargo.length(); i++) {
            System.out.print("-");
        }
        System.out.print(" + ");
        for (int i = 0; i < nombreMasLargo.length(); i++) {
            System.out.print("-");
        }
        System.out.println(" +");
    }
    
    public static int seleccionNumeroRondas() throws ScriptException {
        Scanner sc = new Scanner(System.in);
        String opcion;
        int rondas = 3;
        do {
            System.out.println("1. Partida rápida (3)");
            System.out.println("2. Partida corta (5)");
            System.out.println("3. Partida larga (10)");
            System.out.print("Número de rondas: ");
        
            opcion = sc.next();
        
            // Switch
            switch (opcion) {
                case "1":
                    rondas = 3;
                    break;
                case "2":
                    rondas = 5;
                    break;
                case "3":
                    rondas = 10;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (!opcion.matches("[123]")); // Repetir solo en default
        
        return rondas;
    }  
    
    public static void jugarPartida() throws ScriptException, FileNotFoundException {
        ArrayList jugadores = seleccionJugadores();
        int rondas = seleccionNumeroRondas();

        Partida partida = new Partida(jugadores, rondas);
        partida.jugarPartida();

        partida.actualizarHistorico();
        imprimirRanking();
    }
    
    public static void menuJugadores() throws FileNotFoundException, ScriptException {
        Scanner sc = new Scanner(System.in);
        String opcion;
        do {
            // Mostrar opciones
            System.out.println("1. Ver jugadores");
            System.out.println("2. Añadir jugador");
            System.out.println("3. Eliminar jugador");
            System.out.println("4. Volver");

            // Leer opción
            opcion = sc.next();
            
            // Switch
            switch (opcion) {
                case "1":
                    // Acceder registro y mostrar por pantalla
                    verJugadores();
                    break;
                case "2":
                    // Acceder registro y modificarlo
                    añadirJugador();
                    break;
                case "3":
                    // Acceder registro y modificarlo
                    eliminarJugador();
                    break;
                case "4":
                    // Salir del bucle (volverá al menú principal por efecto del bucle de menuPrincipal)
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (!opcion.matches("4"));
    }
    
    public static void verJugadores() {
        try {
            String registro = Storage.leerRegistro();
            System.out.println(registro);
        }
        catch (FileNotFoundException e) {
            System.out.println("No hay jugadores registrados");
        }
    }
    public static void añadirJugador() {
        System.out.print("Nombre: ");
        Scanner sc = new Scanner(System.in);
        String nombre = sc.next();
        
        // Validar que no existe ya y no es una cadena vacía
        if (!jugadorYaRegistrado(nombre) && !nombre.equals("")) {
            try {
                Storage.añadirJugadorRegistro(nombre);
                System.out.println("Jugador añadido!");
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Jugador no válido!");
        }
        
        
    }
    public static boolean jugadorYaRegistrado(String jugador) {
        boolean yaRegistrado = false;
        try {
            String registroStr = Storage.leerRegistro();
            for (String jugadorRegistrado : registroStr.split("\n")) {
                if (jugador.matches(jugadorRegistrado)) {
                    yaRegistrado = true;
                    break;
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return yaRegistrado;
    }
    
    public static void eliminarJugador() {
        verJugadores();
            
        System.out.print("Eliminar jugador: ");
        Scanner sc = new Scanner(System.in);
        String nombre = sc.next();
        
        try {
            boolean eliminado = Storage.eliminarJugadorRegistro(nombre);
            if (eliminado) {
                System.out.println("Jugador eliminado!");
            } else {
                System.out.println("No existe el jugador!");
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static Hashtable<String, Integer> calcularRanking() throws FileNotFoundException {
        String historicoStr = Storage.leerHistorico();
        String[] historicoArr = historicoStr.split("\n");
        Hashtable<String, Integer> ranking = new Hashtable<>();
        
        // Recorrer el array (nivel partida)
        for (String fila : historicoArr) {
            // Recorrer la línea (nivel jugador: puntuación)
            String[] info = fila.split(", ");
            for (int i = 0; i < info.length; i++) { // Código solo válido para partidas de tres jugadores!!!!
                String nombre = info[i].split(" ")[0];
                String puntosStr = info[i].split(" ")[1];
                int puntos = Integer.parseInt(puntosStr);
                // Añadir jugador a la Hashtable si no existe
                if (!ranking.containsKey(nombre)) {
                    ranking.put(nombre, 0); // Inicializar a 0 puntos
                }
                // Sumar la puntuación 
                int value = ranking.get(nombre);
                int nuevoValue = value + puntos;
                ranking.put(nombre, nuevoValue);
            }
        }
        return ranking;
    }
    
    public static void imprimirRanking() {
        try {
            // Calcular ranking
            Hashtable<String, Integer> ranking = calcularRanking();
            
            // Transformar Hashtable a String
            String rankingStr = "";
            Enumeration<String> rankingKeys = ranking.keys();
            while (rankingKeys.hasMoreElements()) {
                String k = rankingKeys.nextElement();
                int v = ranking.get(k);
                rankingStr += k + ": " + v;
                if (rankingKeys.hasMoreElements()) {
                    rankingStr += "\n";
                }
            }
            System.out.println("*** RANKING ***");
            System.out.println(rankingStr);
            System.out.println();
        }
        catch (FileNotFoundException e) {
            System.out.println("Error al acceder al ranking!");
            System.out.println();
        }
    }
    
    public static void imprimirHistorico() {
        try {
            String historico = Storage.leerHistorico();
            System.out.println("*** HISTÓRICO ***");
            System.out.println(historico);
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("Histórico de partidas no existe!");
            System.out.println();
        }
    }
}
