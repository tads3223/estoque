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
public class ItemVendaId {
    
    private Long id;
    @ManyToOne
    private PedidoVenda pedidoVenda;

    public ItemVendaId() {
    }

    public ItemVendaId(Long id, PedidoVenda pedidoVenda) {
        this.id = id;
        this.pedidoVenda = pedidoVenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PedidoVenda getPedidoVenda() {
        return pedidoVenda;
    }

    public void setPedidoVenda(PedidoVenda pedidoVenda) {
        this.pedidoVenda = pedidoVenda;
    }
    
}
