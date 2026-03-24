/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio14.videojuegosherenciamultiyagregacion;

/**
 *
 * @author andre
 */
public class jefeFinal extends Enemigo {
    private String habilidadEspecial;

    public jefeFinal(String habilidadEspecial, int danio, String nombre, int salud) {
        super(danio, nombre, salud);
        this.habilidadEspecial = habilidadEspecial;
    }

}
