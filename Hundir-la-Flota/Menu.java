package HundirLaFlotaPOO;

import java.util.Scanner;

/**
 *
 * @author mikel
 */
public class Menu {
    // Menu
    
    public static void mainMenu() {
        Scanner sc = new Scanner(System.in);
        String input;
        do {
            // Mostrar menú de inicio
            System.out.println("=================");
            System.out.println(" Hundir la Flota ");
            System.out.println("=================");
            System.out.println("1. Nueva partida");
            System.out.println("2. Salir");

            // Leer input como string
            input = sc.next();

            // Opciones
            switch (input) {
                case "1":
                    // Seleccionar dificultad
                    int dificultad = Menu.elegirDificultad();
                    // Jugar partida
                    GameController.jugar(dificultad);
                    break;
                case "2":
                    // Salir
                    break;
                default:
                    // Input no válido
                    System.out.println(ConsoleColors.RED + "Opción no válida!" + ConsoleColors.RESET);
                    break;
            }
        } while (!input.equals("2"));
    }
    
    public static int elegirDificultad() {
        Scanner sc = new Scanner(System.in);
        String input;
        do {
            System.out.println("\n===================");
            System.out.println(" Elegir dificultad ");
            System.out.println("===================");
            System.out.println("1. Fácil");
            System.out.println("2. Medio");
            System.out.println("3. Difícil");
            
            input = sc.next();
            
            if (!input.matches("[123]")) {
                // Input no válido
                System.out.println(ConsoleColors.RED + "Opción no válida!" + ConsoleColors.RESET);
            }
            
        } while (!input.matches("[123]"));
        
        return Integer.parseInt(input);
    }
}
