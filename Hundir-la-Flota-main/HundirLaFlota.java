package hundirlaflota;

import java.util.Arrays;
import java.util.Scanner;

public class HundirLaFlota {
    
    public static void main(String[] args) {
        // Inicializar juego
        char[][] tablero;
        int disparos;
        int nivel;
        // Mostrar menú
        menu();
        // Elegir nivel
        nivel = elegirNivelDeDificultad();
        // Generar juego
        tablero = generarTablero(nivel);
        disparos = generarDisparos(nivel);
        
        // Jugar juego
        String coordenada;
        int[] indices;
        int x, y;
        while (disparos > 0) {
            // Mostrar tablero jugador (con barcos ocultos)
            mostrarTableroJugador(tablero);
            System.out.println("Disparos: " + disparos);
            // Leer coordenada 
            coordenada = introducirCoordenada();
            // Validar coordenada
            if (validarCoordenada(coordenada)) {
                // Transformar
                indices = transformarCoordenada(coordenada);
                x = indices[0];
                y = indices[1];
                // Disparar
                if (coordenadaNoJugada(tablero, x, y)) {
                    disparar(tablero, x, y);
                    disparos--;
                }
                // Comprobar si quedan barcos por hundir
                if (!quedanBarcos(tablero)) {
                    break;
                }
            }
        }
        
        // Finalizar partida
        if (disparos == 0 && quedanBarcos(tablero)) {
            // Perder
            mostrarTablero(tablero);
            System.out.println("HAS PERDIDO...");
        } else {
            // Ganar
            mostrarTablero(tablero);
            System.out.println("TÚ GANAS!!!");
        }
    }
    
    // Lógica
    
    public static char[][] crearTablero() {
        char[][] tablero = new char[10][10];
        for (int i = 0; i < tablero.length; i++) {
            Arrays.fill(tablero[i], '-');
        }
        return tablero;
    }
    
    public static char[][] generarTablero(int nivel) {
        // Genera el tablero y lo devuelve
        char[][] tablero = crearTablero();
        switch (nivel) {
            case 1:
                insertarPortaaviones(tablero, 1);
                insertarAcorazados(tablero, 1);
                insertarBuques(tablero, 3);
                insertarLanchas(tablero, 5);
                mostrarTablero(tablero);
                break;
            case 2:
                insertarPortaaviones(tablero, 1);
                insertarAcorazados(tablero, 1);
                insertarBuques(tablero, 1);
                insertarLanchas(tablero, 2);
                mostrarTablero(tablero);
                break;
            case 3:
                insertarPortaaviones(tablero, 0);
                insertarAcorazados(tablero, 0);
                insertarBuques(tablero, 1);
                insertarLanchas(tablero, 1);
                mostrarTablero(tablero);
                break;
        }
        return tablero;
    }
    
    public static int generarDisparos(int nivel){
        int disparos;
        switch (nivel) {
            case 1:
                disparos = 50;
                break;
            case 2:
                disparos = 30;
                break;
            case 3:
                disparos = 10;
                break;
            default:
                disparos = 50;
                break;
        }
        return disparos;
    }
    
    public static void insertarLanchas(char[][] tablero, int lanchas) {        
        int x, y;
        while (lanchas > 0) {
            x = numeroAleatorio();
            y = numeroAleatorio();
            if (tablero[x][y] == '-') {
                tablero[x][y] = 'L';
                lanchas--;
            }
            // Si la coordenada está ya cogida, repetimos el proceso y no restamos ningún valor a las lanchas
        }
    }
    public static void insertarBuques(char[][] tablero, int buques) {
        int x, y;
        while (buques > 0) {
            x = numeroAleatorio();
            y = numeroAleatorio();
            // Si todas las coordenadas entran en el tablero
            if (x + 1 < 10) {
                boolean casillasLibres = casillasLibres(tablero, x, y, 1, 0);
                // Si todas las coordenadas están libres
                if (casillasLibres) {
                    introducirBarco(tablero, x, y, 1, 0, 'B');
                    buques--;
                }
            } else if (y + 1 < 10){
                boolean casillasLibres = casillasLibres(tablero, x, y, 0, 1);
                // Si todas las coordenadas están libres
                if (casillasLibres) {
                    introducirBarco(tablero, x, y, 0, 1, 'B');
                    buques--;
                }
            }
        }
        
    }
    public static void insertarAcorazados(char[][] tablero, int acorazados) {
        int x, y;
        while (acorazados > 0) {
            x = numeroAleatorio();
            y = numeroAleatorio();
            // Si todas las coordenadas entran en el tablero
            if (y + 3 < 10) {
                boolean casillasLibres = casillasLibres(tablero, x, y, 0, 3);
                // Si todas las coordenadas están libres
                if (casillasLibres) {
                    introducirBarco(tablero, x, y, 0, 3, 'Z');
                    acorazados--;
                }
            }
        }
    }
    public static void insertarPortaaviones(char[][] tablero, int portaaviones) {
        int x, y;
        while (portaaviones > 0) {
            x = numeroAleatorio();
            y = numeroAleatorio();
            // Si todas las coordenadas entran en el tablero
            if (x + 4 < 10) {
                boolean casillasLibres = casillasLibres(tablero, x, y, 4, 0);
                // Si todas las coordenadas están libres
                if (casillasLibres) {
                    introducirBarco(tablero, x, y, 4, 0, 'P');
                    portaaviones--;
                }
            }
        }
    }
    public static boolean casillasLibres(char[][] tablero, int x, int y, int abajo, int dcha) {
        // x, y: casilla de origen
        // Devolverá true en caso de que todas las casillas a partir de la de origen estén libres. 
        boolean casillasLibres = true;
        for (int i = x; i <= x + abajo; i++) {
            if (tablero[i][y] != '-') {
                casillasLibres = false;
                break;
            }
        }
        for (int j = y; j <= y + dcha; j++) {
            if (tablero[x][j] != '-') {
                casillasLibres = false;
                break;
            }
        }
        return casillasLibres;
    }
    public static void introducirBarco(char[][] tablero, int x, int y, int abajo, int dcha, char barco) {
        for (int i = x; i <= x + abajo; i++) {
            tablero[i][y] = barco;
        }
        for (int j = y; j <= y + dcha; j++) {
            tablero[x][j] = barco;
        }
    }
    
    public static int numeroAleatorio() {
        int n = (int) (Math.random() * (10));
        return n;
    }
    
    public static void disparar(char[][] tablero, int x, int y) {
        // Comprobar si hay barco y modificar tablero
        char letraBarco = tablero[x][y];
        if (tablero[x][y] != '-') {
            // Comprobar si es tocado o hundido
            tablero[x][y] = 'H';
            System.out.println("Hundido!");
        } else {
            tablero[x][y] = 'A';
            System.out.println("Agua!");
        }
    }
    
    public static boolean coordenadaNoJugada(char[][] tablero, int x, int y) {
        // Validar que no se haya dicho ya
        boolean noJugada = false;
        if (tablero[x][y] != 'A' && tablero[x][y] != 'H') {
            noJugada = true;
        } else {
            System.out.println("Coordenada ya jugada");
        }
        return noJugada;
    }
    
    public static boolean quedanBarcos(char[][] tablero) {
        boolean quedanBarcos = false;
        // Recorrer tablero
        int i = 0;
        int j = 0;
        outerloop:
        for (i = 0; i < tablero.length; i++) {
            for (j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == 'L' || tablero[i][j] == 'B' || tablero[i][j] == 'Z' || tablero[i][j] == 'P' ) {
                    quedanBarcos = true;
                    break outerloop;
                }
            }
        }
        //System.out.println("Hay barco en " + i +", " + j);
        return quedanBarcos;
    }
    
    // Funciones de mostrar en pantalla
    
    public static void menu() {
        System.out.println("=== HUNDIR LA FLOTA ===");
        System.out.println("1. Fácil");
        System.out.println("2. Medio");
        System.out.println("3. Difícil");
        System.out.print("Selecciona nivel de dificultad: ");
    }
    
    public static void mostrarTablero(char[][] tablero) {
        System.out.println("\n  0 1 2 3 4 5 6 7 8 9 ");
        for (int i = 0; i < tablero.length; i++) {
            switch (i) {
                case 0: 
                    System.out.print("A ");
                    break;
                case 1: 
                    System.out.print("B ");
                    break;
                case 2: 
                    System.out.print("C ");
                    break;
                case 3: 
                    System.out.print("D ");
                    break;
                case 4: 
                    System.out.print("E ");
                    break;
                case 5: 
                    System.out.print("F ");
                    break;
                case 6: 
                    System.out.print("G ");
                    break;
                case 7: 
                    System.out.print("H "); 
                    break;
                case 8: 
                    System.out.print("I "); 
                    break;
                case 9: 
                    System.out.print("J "); 
                    break;
                default: break;
            }
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void mostrarTableroJugador(char[][] tablero) {
        System.out.println("\n  0 1 2 3 4 5 6 7 8 9 ");
        for (int i = 0; i < tablero.length; i++) {
            switch (i) {
                case 0: 
                    System.out.print("A ");
                    break;
                case 1: 
                    System.out.print("B ");
                    break;
                case 2: 
                    System.out.print("C ");
                    break;
                case 3: 
                    System.out.print("D ");
                    break;
                case 4: 
                    System.out.print("E ");
                    break;
                case 5: 
                    System.out.print("F ");
                    break;
                case 6: 
                    System.out.print("G ");
                    break;
                case 7: 
                    System.out.print("H "); 
                    break;
                case 8: 
                    System.out.print("I "); 
                    break;
                case 9: 
                    System.out.print("J "); 
                    break;
                default: break;
            }
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == 'L' || tablero[i][j] == 'B' || tablero[i][j] == 'Z' || tablero[i][j] == 'P') {
                    System.out.print("- ");
                } else {
                    System.out.print(tablero[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    
    // Funciones auxiliares (validación y transformación inputs)   
    
    public static String introducirCoordenada() {
        System.out.print("Disparar a coordenada: ");
        Scanner in = new Scanner(System.in);
        String coordenada = in.next();
        return coordenada;
    }
    
    public static boolean validarCoordenada(String coordenada) {
        if (coordenada.matches("[a-jA-J]\\d")) {
            return true;
        } else {
            System.out.println("Formato de la coordenada incorrecto:" + coordenada);
            return false;
        }
    }
    
    public static int[] transformarCoordenada(String coordenada) {
        // Extraer letra, transformarla y extraer número
        char letra = extraerLetraCoordenada(coordenada);
        int x = letraANumero(letra);
        int y = extraerNumeroCoordenada(coordenada);
        // Guardamos x e y como los elementos 0 y 1 del array
        int[] indices = new int[2];
        indices[0] = x;
        indices[1] = y;
        return indices;
    }

    public static char extraerLetraCoordenada(String coordenada) {
        char[] array = coordenada.toCharArray();
        return array[0];
    }
    public static int extraerNumeroCoordenada(String coordenada) {
        char[] array = coordenada.toCharArray();
        int numero = Character.getNumericValue(array[1]);
        return numero;
    }
    
    public static int letraANumero(char letra) {
        int numero;
        letra = Character.toUpperCase(letra);
        switch (letra) {
            case 'A':
                numero = 0; break;
            case 'B':
                numero = 1; break;
            case 'C':
                numero = 2; break;
            case 'D':
                numero = 3; break;
            case 'E':
                numero = 4; break;
            case 'F':
                numero = 5; break;
            case 'G':
                numero = 6; break;
            case 'H':
                numero = 7; break;
            case 'I':
                numero = 8; break;
            case 'J':
                numero = 9; break;
            default:
                numero = -1; break;
        }
        return numero;
    }
    
    public static int elegirNivelDeDificultad() {
        Scanner in = new Scanner(System.in);
        // Validación input correcto
        String nivel;
        do {
            nivel = in.next();
            if (!nivel.matches("[123]")) {
                System.out.println("\nOpción no válida\n");
                menu();
            }
        } while(!nivel.matches("[123]"));
        // Transformación a tipo integer
        int nivelNum = Integer.valueOf(nivel);
        return nivelNum;
    }
}
