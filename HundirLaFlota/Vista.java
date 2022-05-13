package HundirLaFlotaPOO;

/**
 *
 * @author mikel
 */
public class Vista {
    
    public static void pintarTablero(String[][] tablero, boolean ocultar) {
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
                // Extraer solo primer caracter
                char letra = tablero[i][j].toCharArray()[0];
                if (ocultar) {
                    if (tablero[i][j].matches("[LBZP].")) {
                        System.out.print("- "); // Ocultar barcos
                    } else {
                        System.out.print(letra + " "); // No ocultar "A", "T", "H"
                    }
                } else {
                    System.out.print(letra + " ");
                }
            }
            System.out.println(); // Fin de fila
        }
        System.out.println(); // Fin de tablero
    }
    
    public static void pintarDisparosRestantes(int disparos) {
        System.out.println("\n=====================");
        System.out.println(" Disparos: " + disparos);
        System.out.println("=====================");
    }
    
    public static void pintarMensajeTrasDisparo(char codigoDisparo) {
        switch (codigoDisparo) {
            case 'T':
                System.out.println(ConsoleColors.YELLOW + "Tocado!" + ConsoleColors.RESET);
                break;
            case 'H':
                System.out.println(ConsoleColors.GREEN + "Hundido!" + ConsoleColors.RESET);
                break;
            default: // 'A'
                System.out.println("Agua!");         
        }
    }
    
}
