/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio14.videojuegosherenciamultiyagregacion;

/**
 *
 * @author andre
 */
public class Enemigo extends Personaje{
    private int danio;

    public Enemigo(int danio, String nombre, int salud) {
        super(nombre, salud);
        this.danio = danio;
    }

    
}
