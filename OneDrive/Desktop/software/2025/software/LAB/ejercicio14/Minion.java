/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio14.videojuegosherenciamultiyagregacion;

/**
 *
 * @author andre
 */
public class Minion extends Enemigo {
    private boolean esVolador;

    public Minion(boolean esVolador, int danio, String nombre, int salud) {
        super(danio, nombre, salud);
        this.esVolador = esVolador;
    }

}
