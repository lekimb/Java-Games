/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saberyganar;

import java.io.IOException;
import java.util.*;
import java.time.LocalDateTime;
import javax.script.ScriptException;

/**
 *
 * @author mikel
 */
public class Partida {
    ArrayList<Jugador> jugadores;
    int rondaActual;
    
    final int rondas;
    final LocalDateTime fecha;
    
    public Partida(ArrayList jugadores, int rondas) {
        this.jugadores = jugadores;
        this.rondaActual = 0;
        this.rondas = rondas;
        this.fecha = LocalDateTime.now();
        
        shuffleJugadores();
    }
    
    public final void shuffleJugadores () {
        Random rand = new Random();
        for (int i = 0; i < jugadores.size(); i++) {
            // Generar número aleatorio
            int randomIndexToSwap = rand.nextInt(jugadores.size());
            // Seleccionar elemento aleatorio (almacenarlo en tmp)
            Jugador tmp = jugadores.get(randomIndexToSwap);
            // Sustituir elemento aleatorio por elemento actual
            jugadores.set(randomIndexToSwap, jugadores.get(i));
            // Sustituir elemento actual por elemento aleatorio
            jugadores.set(i, tmp);
        }
    }
    
    public void imprimirJugadores() {
        System.out.println("*** JUGADORES ***");
        for (Jugador j : jugadores) {
            j.imprimirJugador();
        }
    }
    
    public void imprimirPuntuaciones() {
        System.out.println("*** PUNTUACIONES ***");
        for (Jugador j : jugadores) {
            j.imprimirPuntos();
        }
    }
    
    public void jugarRonda() throws ScriptException {
        for (Jugador jugador : jugadores) {
            System.out.println("Turno de " + jugador.nombre);
            // Instanciar pregunta (idealmente, de forma aleatoria)
            Pregunta pregunta = Pregunta.preguntaMates();
            // Responder
            boolean correcta = jugador.responderPregunta(pregunta);
            // Sumar punto (o no)
            if (correcta) {
                jugador.sumarPunto();
            } 
        }
        // Fin de la ronda actual
        rondaActual += 1;
    }
    
    public void jugarPartida() throws ScriptException {
        while (rondaActual < rondas) {
            // Jugar ronda
            jugarRonda();
            // Imprimir puntuaciones 
            imprimirPuntuaciones();
        }
    }
    
    public void actualizarHistorico() {
        try {
            Storage.añadirPartidaHistorico(datosPartidaStr());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            
        }
    }
    
    public String datosPartidaStr() {
        String datos = "";
        for (int i = 0; i < jugadores.size(); i++) {
            datos += jugadores.get(i).nombre + " " + jugadores.get(i).puntos;
            if (i < jugadores.size() - 1) {
                datos += ", ";
            }
        }
        return datos;
    }
    
    public void imprimirFecha() {
        System.out.print(fecha.getDayOfMonth() + " ");
        System.out.print(fecha.getMonth() + " ");
        System.out.print(fecha.getYear() + ", ");
        System.out.print(fecha.getHour() + ":");
        System.out.println(fecha.getMinute());
    }
}
