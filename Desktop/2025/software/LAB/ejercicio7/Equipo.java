/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio7.agrgacionfutbol;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andre
 */
public class Equipo {
    private List<Jugador> jugadores;

    public Equipo() {
        this.jugadores = new ArrayList<>();
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public void eliminarJugador(Jugador jugador) {
        jugadores.remove(jugador);
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    @Override
    public String toString() {
        return "Equipo formado por los Jugadores :" + jugadores;
    }

}
