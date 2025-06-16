/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.dto;

/**
 *
 * @author 1513003
 */
public class SubgrupoProdutoResponse {
    
    private Long id;
    private String nome;
    
    private SubgrupoParent grupoProduto;

    public SubgrupoProdutoResponse() {
    }

    public SubgrupoProdutoResponse(Long id, String nome, SubgrupoParent grupoProduto) {
        this.id = id;
        this.nome = nome;
        this.grupoProduto = grupoProduto;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public SubgrupoParent getGrupoProduto() {
        return grupoProduto;
    }
}
