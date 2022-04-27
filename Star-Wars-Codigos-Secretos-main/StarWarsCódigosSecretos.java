package starwarscódigossecretos;
import java.util.Scanner;

public class StarWarsCódigosSecretos {

    public static void main(String[] args) {
        inicio();
    }
    
    public static void inicio() {
        System.out.println("=== STAR WARS CÓDIGOS SECRETOS ===");
        System.out.println("Hace mucho tiempo, en una galaxia muy, muy lejana... La Princesa Leia, Luke Skywalker, Han Solo, Chewbacca, C3PO y R2D2 viajan en una nave imperial robada en una misión secreta para infiltrarse en otra estrella de la muerte que el imperio está construyendo para destruirla.");
        System.out.println("(Presiona Intro para continuar)");
        Scanner input = new Scanner(System.in);
        input.nextLine(); 
        nivel1();
    }
    
    public static void nivel1() {
        int s1 = (int) (Math.random() * (10 + 1));
        int s2 = (int) (20 + Math.random() * (30 - 20 + 1));
        
        int sumatorio = 0;
        for (int i = s1; i <= s2; i++) {
            sumatorio += i;
        }
        
        System.out.println("Los problemas empiezan cuando deben realizar un salto hiperespacial hasta al sistema " + s1 + " en el sector " + s2 + ", pero el sistema de navegación está estropeado y el computador tiene problemas para calcular parte de las coordenadas de salto. Chewbacca, piloto experto, se da cuenta que falta el cuarto número de la serie. Recuerda de sus tiempos en la academia de pilotos que para calcularlo hay que calcular el sumatorio entre el no del sistema y el no del sector (ambos inclusive).");
        
        System.out.println("¿Qué debe introducir?");
        Scanner input = new Scanner(System.in);
        int respuesta = input.nextInt();
        if (respuesta == sumatorio) {
            nivel2();
        } else {
            perder();
        }
    }
    
    public static void nivel2() {
        int p1 = (int) (Math.random() * (7 + 1));
        int p2 = (int) (8 + Math.random() * (12 - 8 + 1));
        
        int productorio = 1;
        for (int i = p1; i <= p2; i++) {
            productorio *= i;
        }
        
        System.out.println("Gracias a Chewbacca consiguen llegar al sistema correcto y ven a lo lejos la estrella de la muerte. Como van en una nave imperial robada se aproximan lentamente con la intención de pasar desapercibidos. De repente suena el comunicador. \"Aquí agente de espaciopuerto " + p1 + " contactando con nave imperial " + p2 +". No están destinados en este sector. ¿Qué hacen aquí?\". Han Solo coge el comunicador e improvisa. \"Eh... tenemos un fallo en el... eh... condensador de fluzo... Solicitamos permiso para atracar y reparar la nave\". El agente, que no se anda con tonterías, responde \"Proporcione código de acceso o abriremos fuego\". Han Solo ojea rápidamente el manual del piloto que estaba en la guantera y da con la página correcta. El código es el productorio entre el nº del agente y el nº de la nave (ambos inclusive).");
        
        System.out.println("¿Cuál es el código?");
        Scanner input = new Scanner(System.in);
        int respuesta = input.nextInt();
        if (respuesta == productorio) {
            nivel3();
        } else {
            perder();
        }
    }
    
    public static void nivel3() {
        int n = (int) (50 + Math.random() * (100 - 50 + 1));
        int nEntreDiez = n/10;

        int clave = 1;
        for (int i = 1; i <= nEntreDiez; i++) {
            clave *= i;
        }

        System.out.println("Han Solo proporciona el código correcto. Atracan en la estrella de la muerte, se equipan con trajes de soldados imperiales que encuentran en la nave para pasar desapercibidos y bajan. Ahora deben averiguar en qué nivel de los " + n + " existentes se encuentra el reactor principal. Se dirigen al primer panel computerizado que encuentran y la Princesa Leia intenta acceder a los planos de la nave pero necesita introducir una clave de acceso. Entonces recuerda la información que le proporcionó Lando Calrissian: \"La clave de acceso a los planos de la nave es el factorial N/10 (redondeando hacia abajo el resultado), donde N es el nº de niveles\"");

        System.out.println("¿Cual es la clave de acceso?");
        Scanner input = new Scanner(System.in);
        int respuesta = input.nextInt();
        if (respuesta == clave) {
            nivel4();
        } else {
            perder();
        }
    }
    
    public static void nivel4() {
        int numero = (int) (10 + Math.random() * (100 - 10 + 1));
        boolean esPrimo = true;
        for (int i = numero - 1; i > 1; i--) {
            if (numero%i == 0) {
                esPrimo = false;
                break;
            }
        }
        
        System.out.println("Gracias a la inteligencia de Leia llegan al nivel correcto y encuentran la puerta acorazada que da al reactor principal. R2D2 se conecta al panel de acceso para intentar hackear el sistema y abrir la puerta. Para desencriptar la clave necesita  verificar si el número " + numero + " es primo o no.");
        System.out.println("Si es primo introduce un 1, si no lo es introduce un 0");
        
        Scanner input = new Scanner(System.in);
        int respuestaNum;
        boolean respuestaBool = false;
        
        do {
            respuestaNum = input.nextInt();
            switch (respuestaNum) {
                case 1:
                    respuestaBool = true;
                    break;
                case 0:
                    respuestaBool = false;
                    break;
                default:
                    System.out.println("Valor inválido, introduce 1 si es primo y 0 si no lo es");
                    break;
            }
        } while (respuestaNum != 0 && respuestaNum != 1);
                
        if (respuestaBool == esPrimo) {
            nivel5();
        } else {
            perder();
        }
        
    }
    
    public static void nivel5() {
        int minutos = (int) (5 + Math.random() * (10 - 5 + 1));
        int segundos = (int) (5 + Math.random() * (10 - 5 + 1));
        
        int factorialMinutos = 1;
        int factorialSegundos = 1;
        for (int i = 1; i <= minutos; i++) {
            factorialMinutos *= i;
        }
        for (int i = 1; i <= segundos; i++) {
            factorialSegundos *= i;
        }
        
        int resultado = factorialMinutos + factorialSegundos;
        
        System.out.println("Consiguen entrar al reactor. Ya solo queda que Luke Skywalker coloque la bomba, programe el temporizador y salir de allí corriendo. Necesita programarlo para que explote en exactamente " + minutos + " minutos y " + segundos + " segundos, el tiempo suficiente para escapar antes de que explote pero sin que el sistema de seguridad anti-explosivos detecte y desactive la bomba. Pero el temporizador utiliza un reloj Zordgiano un tanto peculiar. Para convertir los minutos y segundos al sistema Zordgiano hay que sumar el factorial de minutos y el factorial de segundos.");
        
        System.out.println("¿Qué valor debe introducir?");
        Scanner input = new Scanner(System.in);
        int respuesta = input.nextInt();
        if (respuesta == resultado) {
            ganar();
        } else {
            perder();
        }
    }
    
    public static void ganar() {
        System.out.println("Luke Skywalker introduce el tiempo correcto, activa el temporizador y empiezan a sonar las alarmas. Salen de allí corriendo, no hay tiempo que perder. La nave se convierte en un hervidero de soldados de arriba a abajo y entre el caos que les rodea consiguen llegar a la nave y salir de allí a toda prisa. A medida que se alejan observan por la ventana la imagen de la colosal estrella de la muerte explotando en el silencio del espacio, desapareciendo para siempre junto a los restos del malvado imperio.");
        System.out.println("¡Has salvado la galaxia gracias a la Fuerza Jedi de las Matemáticas! ¡Enhorabuena!");
        fin();
    }
    
    public static void perder() {
        System.out.println("Ese no era el código correcto... La misión ha fracasado. Todavía no eres un Maestro Jedi de las Matemáticas. ¡Vuelve a intentarlo!");
        fin();
    }
    
    public static void fin() {
        System.out.println("Gracias por jugar :D");
    }
}
