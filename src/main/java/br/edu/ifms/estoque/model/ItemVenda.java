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
public class ItemVenda {
    @EmbeddedId
    private ItemVendaId id;
    
    private BigDecimal quantidade;
    private BigDecimal percentualDesconto;
    private BigDecimal valorDesconto;
    
    private Produto produto;

    public ItemVenda() {
    }

    public ItemVendaId getId() {
        return id;
    }

    public void setId(ItemVendaId id) {
        this.id = id;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPercentualDesconto() {
        return percentualDesconto;
    }

    public void setPercentualDesconto(BigDecimal percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
}
