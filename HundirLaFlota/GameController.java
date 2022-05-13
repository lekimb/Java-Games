package HundirLaFlota;

import java.util.Scanner;

/**
 *
 * @author mikel
 */
public class GameController {
    
    public static void jugar(int dificultad) {
        Partida partida = new Partida(dificultad);
        Vista.pintarTablero(partida.getTablero(), false); // For developement only
        
        while (partida.getDisparos() > 0) {
            // Pintar disparos restantes y tablero (Vista)
            Vista.pintarDisparosRestantes(partida.getDisparos());
            Vista.pintarTablero(partida.getTablero(), true); // Ocultar
            
            // Leer y validar input (GameController)
            Coordenada c = leerCoordenada();
            while (!partida.validarCoordenadaNoJugada(c)) {
                System.out.println(ConsoleColors.RED + "Coordenada ya jugada!" + ConsoleColors.RESET);
                c = leerCoordenada();
            }
            
            // Disparar (Partida)
            char codigoDisparo = partida.disparar(c);
            Vista.pintarMensajeTrasDisparo(codigoDisparo);
            
            // Comprobar si quedan barcos (Partida)
            if (!partida.quedanBarcos()) {
                break;
            }
        }
        
        // Mostrar disparos y tablero finales
        Vista.pintarDisparosRestantes(partida.getDisparos());
        Vista.pintarTablero(partida.getTablero(), true); // Barcos ocultos
        // Mostrar tablero inicial
        if (partida.quedanBarcos()) {
            // Perder
            System.out.println(ConsoleColors.RED + "Vaya, te has quedado sin disparos...\n" + ConsoleColors.RESET);
        } else {
            // Ganar
            System.out.println(ConsoleColors.GREEN + "Enhorabuena, has hundido todos los barcos!\n" + ConsoleColors.RESET);
        }
    }
    
    private static Coordenada leerCoordenada() { // REVISAR TIPO DE RETORNO
        Scanner sc = new Scanner(System.in);
        // Input jugador
        String coordenadaStr = sc.next();
        // Validar Formato
        while (!validarFormato(coordenadaStr)) {
            // Si no es correcto el formato, mostrar mensaje y volver a leer input
            System.out.println(ConsoleColors.RED + "Formato de la coordenada incorrecto!" + ConsoleColors.RESET);
            coordenadaStr = sc.next();
        } 
        // Convertir String a Coordenada
        Coordenada c = convertirACoordenada(coordenadaStr);
        return c;
    }
    
    private static boolean validarFormato(String c) {
        return c.matches("^[a-jA-J][0-9]$");
    }
    
    private static Coordenada convertirACoordenada(String c) {
        // Extraer letra, convertirla a mayúscula y transformar a valor numérico
        char letra = Character.toUpperCase(c.toCharArray()[0]);
        int y;
        switch (letra) {
            case 'A':
                y = 0; break;
            case 'B':
                y = 1; break;
            case 'C':
                y = 2; break;
            case 'D':
                y = 3; break;
            case 'E':
                y = 4; break;
            case 'F':
                y = 5; break;
            case 'G':
                y = 6; break;
            case 'H':
                y = 7; break;
            case 'I':
                y = 8; break;
            case 'J':
                y = 9; break;
            default:
                y = -1; break;
        }
        // Extraer número
        int x = Character.getNumericValue(c.toCharArray()[1]);
        // Devolver Coordenada
        return new Coordenada(y, x);
    }
    
//    private static boolean validarCoordenadaNoJugada(Coordenada c) {
//        
//        
//    }
}
