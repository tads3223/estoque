/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

/**
 *
 * @author 1513003
 */
@Entity
public class SubgrupoProduto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    
    @ManyToOne
    private SubgrupoProduto grupoProduto;

    public SubgrupoProduto() {
    }

    public SubgrupoProduto(Long id, String nome, SubgrupoProduto grupoProduto) {
        this.id = id;
        this.nome = nome;
        this.grupoProduto = grupoProduto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public SubgrupoProduto getGrupoProduto() {
        return grupoProduto;
    }

    public void setGrupoProduto(SubgrupoProduto grupoProduto) {
        this.grupoProduto = grupoProduto;
    }
    
}
