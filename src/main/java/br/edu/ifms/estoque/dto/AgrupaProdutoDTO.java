/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.dto;

/**
 *
 * @author 1513003
 */
public class AgrupaProdutoDTO {
    private int quantidadeSubgrupos;
    private Long grupoProdutoId;
    private String nome;

    public AgrupaProdutoDTO(int quantidadeSubgrupos, Long grupoProdutoId, String nome) {
        this.quantidadeSubgrupos = quantidadeSubgrupos;
        this.grupoProdutoId = grupoProdutoId;
        this.nome = nome;
    }

    public int getQuantidadeSubgrupos() {
        return quantidadeSubgrupos;
    }

    public Long getGrupoProdutoId() {
        return grupoProdutoId;
    }

    public String getNome() {
        return nome;
    }
    
}
