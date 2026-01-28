/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio1.herenciavehiculo;

/**
 *
 * @author andre
 */
public class moto extends Vehiculo {
    private String cilindrada;

    public moto(String cilindrada, String marca, String modelo) {
        super(marca, modelo);
        this.cilindrada = cilindrada;
    }

    public String getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(String cilindrada) {
        this.cilindrada = cilindrada;
    }
    
    @Override
    public void mostrarInfo(){}
    
}
