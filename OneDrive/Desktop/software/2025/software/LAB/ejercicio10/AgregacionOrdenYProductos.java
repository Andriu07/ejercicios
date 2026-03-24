/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ejercicio10.agregacionordenyproductos;

/**
 *
 * @author andre
 */
public class AgregacionOrdenYProductos {

    public static void main(String[] args) {
        Producto p1 = new Producto("Zapatos Calvin Klein", 100.00);
        Producto p2 = new Producto("Peluche Pokemon", 25.00);

        Orden orden = new Orden();
        orden.agregarProducto(p1);
        orden.agregarProducto(p2);
    }
}
