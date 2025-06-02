/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

/**
 *
 * @author 1513003
 */
@Entity
public class PedidoVenda {
    
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime emissao;

    public PedidoVenda() {
    }

    public PedidoVenda(Long id, LocalDateTime emissao) {
        this.id = id;
        this.emissao = emissao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getEmissao() {
        return emissao;
    }

    public void setEmissao(LocalDateTime emissao) {
        this.emissao = emissao;
    }
    
    
}
