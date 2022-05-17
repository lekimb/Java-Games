
package wordle;

public class Wordle {

    public static void main(String[] args) {
        
        instruccionesJuego();
        ControladorDeJuego.jugar();
        
    } 
    
    public static void instruccionesJuego() {
        System.out.println("*** WORDLE ***");        
        System.out.println("Adivina la palabra oculta en seis intentos.");
        System.out.println("Cada intento debe ser una palabra válida de 5 letras.");
        System.out.println("Después de cada intento el color de las letras indica lo cerca que estás de acertar la palabra:");
        System.out.println("- " + ConsoleColors.GREEN + "Color verde: " + ConsoleColors.RESET + "la letra está en la palabra y en la posición correcta.");
        System.out.println("- " + ConsoleColors.YELLOW + "Color amarillo: " + ConsoleColors.RESET + "la letra está en la palabra pero en la posición incorrecta.");
        System.out.println("- Color negro: la letra no está en la palabra.");
        System.out.println();
    }
}
