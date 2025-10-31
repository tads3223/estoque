/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.venda;

import br.edu.ifms.estoque.compra.MovimentoProduto;
import br.edu.ifms.estoque.venda.ItemVenda;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author 1513003
 */
@Entity
@DiscriminatorValue("MOVIMENTO_VENDA")
public class MovimentoVenda extends MovimentoProduto {
    @ManyToOne
    private ItemVenda itemVenda;

    public MovimentoVenda() {
    }

    public MovimentoVenda(
            Long id, 
            BigDecimal quantidade, 
            LocalDateTime emissao,
            ItemVenda itemVenda
    ) {
        super(id, quantidade, emissao);
        this.itemVenda = itemVenda;
    }

    public ItemVenda getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(ItemVenda itemVenda) {
        this.itemVenda = itemVenda;
    }
}
