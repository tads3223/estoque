/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

/**
 *
 * @author 1513003
 */
@Embeddable
public class ItemCompraId {
    private Long id;
    
    @ManyToOne
    private PedidoCompra pedidoCompra;

    public ItemCompraId() {
    }

    public ItemCompraId(Long id, PedidoCompra pedidoCompra) {
        this.id = id;
        this.pedidoCompra = pedidoCompra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PedidoCompra getPedidoCompra() {
        return pedidoCompra;
    }

    public void setPedidoCompra(PedidoCompra pedidoCompra) {
        this.pedidoCompra = pedidoCompra;
    }
}
