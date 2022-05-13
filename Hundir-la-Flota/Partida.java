package HundirLaFlotaPOO;

import java.util.Arrays;

/**
 *
 * @author mikel
 */
public class Partida {
    private int disparos;
    private String[][] tablero;
//    private String[][] tableroActual;

    public Partida (int dificultad) {
        generarDisparos(dificultad);
        generarTablero(dificultad);
    }
    
    // Getters
    public int getDisparos() {
        return this.disparos;
    }
    
    public String[][] getTablero() {
        return this.tablero;
    }
    
//    public String[][] getTableroActual() {
//        return this.tableroActual;
//    }
    
    private void generarDisparos(int dificultad) {
        switch (dificultad) {
            case 1:
                this.disparos = 50;
                break;
            case 2:
                this.disparos = 30;
                break;
            default:
                this.disparos = 10;
                break;
        }
    }
    private void generarTablero(int dificultad) {
        // Crear tablero de 10 x 10
        this.tablero = new String[10][10];
        for (int i = 0; i < tablero.length; i++) {
            Arrays.fill(tablero[i], "-");
        }

        // Insertar barcos según dificultad
        switch (dificultad) {
            case 1:
                insertarBarcos(1, "P");
                insertarBarcos(1, "Z");
                insertarBarcos(3, "B");
                insertarBarcos(5, "L");
                break;
            case 2:
                insertarBarcos(1, "P");
                insertarBarcos(1, "Z");
                insertarBarcos(1, "B");
                insertarBarcos(2, "L");
                break;
            default:
                insertarBarcos(1, "B");
                insertarBarcos(1, "L");
                break;
        }
    }
    
//    private void insertarLanchas(int n) {
//        String codigoBarco = "L";
//        Coordenada c;
//        for (int i = 0; i < n; i++) {
//            String codigoUnico = codigoBarco + i; // Generar código único para cada barco
//            do {
//                // Generar coordenada aleatoria
//                c = coordenadaAleatoria();
//                // Repetir el proceso si la casilla en coordenada generada está ocupada
//            } while (!casillaLibre(c));
//            // Insertar barco 
//            insertarBarco(c, codigoUnico);
//        }
//    }
    
    // (...)
//    private void insertarBuques(int n) {
//        String codigoBarco = "B";
//        Coordenada c;
//        Coordenada d;
//        Coordenada[] arr = new Coordenada[2];
//        for (int i = 0; i < n; i++) {
//            String codigoUnico = codigoBarco + i;
//            do {
//                // Generar coordenada aleatoria
//                c = coordenadaAleatoria();
//                // Generar segunda coordenada;
//                d = new Coordenada(c.y, c.x + 1);
//                // Añadir coordenadas al array
//                arr[0] = c; 
//                arr[1] = d;
//            } while (c.x + 1 >= 10 || !casillasLibres(arr));
//            // Insertar barco
//            for (Coordenada coordenada : arr) {
//                insertarBarco(coordenada, codigoUnico);
//            }
//        }
//    }
    
    private void insertarBarcos(int n, String codigoBarco) {
        // Dirección del barco
        char direccion;
        if (codigoBarco.matches("^[LZP]$")) { // Lancha, acoraZado, Portaaviones
            direccion = 'v';
        } else { // Buque
            direccion = 'h';
        }
        // Número de casillas que ocupa el barco
        int casillas;
        switch (codigoBarco) {
            case "L":
                casillas = 1; break;
            case "B":
                casillas = 2; break;
            case "Z":
                casillas = 4; break;
            default:
                casillas = 5; break;
        }
        
        Coordenada c;
        Coordenada[] arr = new Coordenada[casillas];
        for (int i = 0; i < n; i++) {
            String codigoUnico = codigoBarco + i;
            int coordenadaBase = 0;
            do {
                // Generar coordenada aleatoria y añadir al array
                c = coordenadaAleatoria();
                arr[0] = c;
                // Generar siguientes coordenadas (si las hay) y añadir al array
                for (int j = 1; j < arr.length; j++) {
                    if (direccion == 'v') {
                        arr[j] = new Coordenada(c.y + j, c.x); // vertical
                        coordenadaBase = c.y;
                    } else {
                        arr[j] = new Coordenada(c.y, c.x + j); // horizontal
                        coordenadaBase = c.x;
                    }
                }
            } while (coordenadaBase + casillas - 1 >= 10 || !casillasLibres(arr));
            // Insertar barco
            for (Coordenada coordenada : arr) {
                insertarBarco(coordenada, codigoUnico);
            }
        }
    }
    
    static private Coordenada coordenadaAleatoria() {
        int y = (int) (Math.random() * 10);
        int x = (int) (Math.random() * 10);
        return new Coordenada(y, x);
    }
    
    private boolean casillaLibre(Coordenada c) {
        return "-".equals(tablero[c.y][c.x]);
    }
    
    private boolean casillasLibres(Coordenada[] coordenadas) {
        boolean casillasLibres = true;
        for (Coordenada c : coordenadas) {
            if (!casillaLibre(c)) {
                casillasLibres = false;
            }
        }
        return casillasLibres;
    }
    
    private void insertarBarco(Coordenada c, String codigoBarco) {
        tablero[c.y][c.x] = codigoBarco;
    }
    
    public char disparar(Coordenada c) {
        char codigoDisparo;
        // Si no hay barco, modificar tablero y notificar 'Agua'
        if ("-".equals(tablero[c.y][c.x])) {
            tablero[c.y][c.x] = "A";
            codigoDisparo = 'A';
        } else {
            // Si hay barco, modificar tablero y notificar 'Tocado' o 'Hundido'
            String codigoBarco = tablero[c.y][c.x];
            // Comprobar si es el último barco con su mismo código
            if (ultimoDeSuEspecie(c, codigoBarco)) {
                // Modificar tablero
                tablero[c.y][c.x] = "H";
                // SE NECESITARÍA TABLERO ORIGINAL PARA MODIFICAR TODAS LAS CASILLAS QUE OCUPABA EL BARCO
                // Notificar por pantalla
                codigoDisparo = 'H';
            } else {
                tablero[c.y][c.x] = "T";
                codigoDisparo = 'T';
            }
        }
        // Restar un disparo
        this.disparos--;
        // Devolver codigo disparo
        return codigoDisparo;
    }
    
    private boolean ultimoDeSuEspecie(Coordenada c, String codigoBarco) {
        boolean ultimo = true;
        if (c.y - 1 >= 0 && codigoBarco.equals(tablero[c.y - 1][c.x])) {
            ultimo = false;
        }
        if (c.y + 1 <= 9 && codigoBarco.equals(tablero[c.y + 1][c.x])) {
            ultimo = false;
        }
        if (c.x - 1 >= 0 && codigoBarco.equals(tablero[c.y][c.x - 1])) {
            ultimo = false;
        }
        if (c.x + 1 <= 9 && codigoBarco.equals(tablero[c.y][c.x + 1])) {
            ultimo = false;
        }
        return ultimo;
    }
    
    
    public boolean quedanBarcos() {
        boolean quedanBarcos = false;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (!"-".equals(tablero[i][j]) && !"A".equals(tablero[i][j]) && !"T".equals(tablero[i][j]) && !"H".equals(tablero[i][j])) {
                    quedanBarcos = true;
                }
            }
        }
        return quedanBarcos;
    }
    
    public boolean validarCoordenadaNoJugada(Coordenada c) {
        // Comprobar que la casilla es distinta a "A", "T" o "H"
        return (!"A".equals(tablero[c.y][c.x]) && !"T".equals(tablero[c.y][c.x]) && !"H".equals(tablero[c.y][c.x]));
    }
}
