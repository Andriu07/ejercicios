/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio5.herenciaempleados;

/**
 *
 * @author andre
 */
public class Gerente extends Empleado{
    private String departamento;

    public Gerente(String departamento, String nombre, double salario) {
        super(nombre, salario);
        this.departamento = departamento;
    }

    
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
}
