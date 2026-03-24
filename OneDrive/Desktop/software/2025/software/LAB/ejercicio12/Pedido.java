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
public class Pedido{
    private String cliente;
    List<ItemPedido> items;

    public Pedido(String cliente, List<ItemPedido> items) {
        this.cliente = cliente;
        this.items = items;
    }


}
