/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio12.sistemadepedidoherenciagregacion;

import java.util.Date;

/**
 *
 * @author andre
 */
public class Alimenticio extends Producto {
     private Date fechadecaducidad;

    public Alimenticio(Date fechadecaducidad, String id, String nombre, double precio) {
        super(id, nombre, precio);
        this.fechadecaducidad = fechadecaducidad;
    }
     
}
