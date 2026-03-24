/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio5.herenciaempleados;

/**
 *
 * @author andre
 */
public class Desarrollador extends Empleado{
    private String lenguaje;

    public Desarrollador(String lenguaje, String nombre, double salario) {
        super(nombre, salario);
        this.lenguaje = lenguaje;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }
}
