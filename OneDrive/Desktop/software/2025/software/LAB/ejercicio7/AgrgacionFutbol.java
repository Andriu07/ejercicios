/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ejercicio7.agrgacionfutbol;

/**
 *
 * @author andre
 */
public class AgrgacionFutbol {

    public static void main(String[] args) {
      Equipo equipo = new Equipo();
        Jugador jugador1 = new Jugador("Raphinha", "Delantero");
        Jugador jugador2 = new Jugador("Lamine", "Defensa");

        equipo.agregarJugador(jugador1);
        equipo.agregarJugador(jugador2);
        System.out.println(equipo);

        equipo.eliminarJugador(jugador1);
        System.out.println(equipo);

    }
}
