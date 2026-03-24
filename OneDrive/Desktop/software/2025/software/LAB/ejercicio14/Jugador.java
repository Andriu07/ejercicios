/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio14.videojuegosherenciamultiyagregacion;

/**
 *
 * @author andre
 */
public class Jugador extends Personaje {
    private int vidas;

    public Jugador(int vidas, String nombre, int salud) {
        super(nombre, salud);
        this.vidas = vidas;
    }
    
}
