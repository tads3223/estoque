/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author 1513003
 */
public class ProdutoResponse {
    
    private Long id;
    private String nome;
    private String descricao;
    private Integer estoqueMinimo;
    
    private BigDecimal preco;
    private BigDecimal estoque;
    private LocalDate dataUltimaCompra;
    
    private SubgrupoProdutoResponse subGrupo;
    private UnidadeMedidaResponse unidadeMedida;
    private MarcaResponse marca;

    public ProdutoResponse() {
    }

    public ProdutoResponse(Long id, String nome) {
        this.id = id;
        this.nome = nome;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(Integer estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getEstoque() {
        return estoque;
    }

    public void setEstoque(BigDecimal estoque) {
        this.estoque = estoque;
    }

    public LocalDate getDataUltimaCompra() {
        return dataUltimaCompra;
    }

    public void setDataUltimaCompra(LocalDate dataUltimaCompra) {
        this.dataUltimaCompra = dataUltimaCompra;
    }

    public SubgrupoProdutoResponse getSubGrupo() {
        return subGrupo;
    }

    public void setSubGrupo(SubgrupoProdutoResponse subGrupo) {
        this.subGrupo = subGrupo;
    }

    public UnidadeMedidaResponse getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedidaResponse unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public MarcaResponse getMarca() {
        return marca;
    }

    public void setMarca(MarcaResponse marca) {
        this.marca = marca;
    }
    
    
}
