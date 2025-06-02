/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import java.math.BigDecimal;

/**
 *
 * @author 1513003
 */
@Entity
public class ItemCompra {
    
    @EmbeddedId
    private ItemCompraId id;
    
    private BigDecimal quantidade;
    private BigDecimal valorUnitario;

    public ItemCompra() {
    }

    public ItemCompra(ItemCompraId id, BigDecimal quantidade, BigDecimal valorUnitario) {
        this.id = id;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public ItemCompraId getId() {
        return id;
    }

    public void setId(ItemCompraId id) {
        this.id = id;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    
}
