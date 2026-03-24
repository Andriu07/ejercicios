/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio12.sistemadepedidoherenciagregacion;

/**
 *
 * @author andre
 */
public class ItemPedido extends Producto{
    private int cantidad;
    private Producto producto;

    public ItemPedido(int cantidad, Producto producto, String id, String nombre, double precio) {
        super(id, nombre, precio);
        this.cantidad = cantidad;
        this.producto = producto;
    }

    
}
