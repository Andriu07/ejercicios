/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio1.herenciavehiculo;

/**
 *
 * @author andre
 */
public class Carro extends Vehiculo{
    private int numPuertas;

    public Carro(int numPuertas, String marca, String modelo) {
        super(marca, modelo);
        this.numPuertas = numPuertas;
    }
    
    public int getNumPuertas() {
        return numPuertas;
    }

    public void setNumPuertas(int numPuertas) {
        this.numPuertas = numPuertas;
    }
    
    @Override
    public void mostrarInfo(){}
}
