/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio14.videojuegosherenciamultiyagregacion;

import java.util.List;

/**
 *
 * @author andre
 */
class Nivel {
    private int numero;
    List<Personaje> personajes;
     private String jefe;

    public Nivel(int numero, List<Personaje> personajes, String jefe) {
        this.numero = numero;
        this.personajes = personajes;
        this.jefe = jefe;
    }

}
