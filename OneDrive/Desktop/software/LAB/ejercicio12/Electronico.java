/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio12.sistemadepedidoherenciagregacion;

/**
 *
 * @author andre
 */
public class Electronico  extends Producto{
    private String garantia;

    public Electronico(String garantia, String id, String nombre, double precio) {
        super(id, nombre, precio);
        this.garantia = garantia;
    }
}
