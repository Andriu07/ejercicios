/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio10.agregacionordenyproductos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andre
 */
public class Orden {
     private List<Producto> productos;

    public Orden() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
}
