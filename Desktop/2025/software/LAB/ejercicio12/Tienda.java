/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio12.sistemadepedidoherenciagregacion;

import java.util.List;

/**
 *
 * @author andre
 */
public class Tienda{
     private String nombre;
    List<Producto> inventario;

    public Tienda(String nombre, List<Producto> inventario) {
        this.nombre = nombre;
        this.inventario = inventario;
    }
}
