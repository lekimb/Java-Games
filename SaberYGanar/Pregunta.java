/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saberyganar;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author mikel
 */
public class Pregunta {
    String enunciado;
    int respuesta;
    String tipo;
    
    public static Pregunta preguntaMates() {
        Pregunta pregunta = new Pregunta();
        pregunta.tipo = "mates";
        
        // Generar número de enteros (entre 4 y 8)
        int numeroEnteros = (int) (4 + Math.random() * (8 - 4 + 1));
        int[] numeros = new int[numeroEnteros];
        // Asignar valores (entre 2 y 12)
        for (int i = 0; i < numeroEnteros; i++) {
            int valor = (int) (2 + Math.random() * (12 - 2 + 1));
            numeros[i] = valor;
        }

        // Operadores (+ - *)
        String[] operadoresTipo = {"+", "-", "*"};
        // Asignar operadores al azar
        String[] operadores = new String[numeroEnteros - 1];
        for (int i = 0; i < (numeroEnteros - 1); i++) {
            int indiceAleatorio = (int) (Math.random() * operadoresTipo.length);
            operadores[i] = operadoresTipo[indiceAleatorio];
        }
        
        // Generar expresión matemática
        String expresion = "";
        for (int i = 0; i < numeroEnteros - 1; i++) {
            expresion += numeros[i];
            expresion += " " + operadores[i] + " ";   
        }
        expresion += numeros[numeros.length - 1]; // Último índice
        
        // Evaluar expresión
        int valor = 0;
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("javascript");
            Object resultado = engine.eval(expresion);
            valor = Integer.decode(resultado.toString());
        }
        catch (ScriptException e) {
            System.out.println(e.getMessage());
        }
        
        // Asignar datos a objeto pregunta
        pregunta.enunciado = expresion + " = ";
        pregunta.respuesta = valor;
        
        return pregunta;
    }
    
    // public static Pregunta preguntaInglés()
    
    // public static Pregunta preguntaLengua()
    
    // public static Pregunta preguntaAleatoria()
}
