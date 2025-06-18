/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author 1513003
 */
public class ProdutoCreateRequest {

    @NotNull
    @NotEmpty
    @NotBlank
    private String nome;
    
    private String descricao;
    private Integer estoqueMinimo;
    
    @NotNull(message = "O atributo SUBGRUPO não foi INFORMADO.")
    private SubgrupoProdutoResponse subGrupo;
    
    @NotNull(message = "O atributo UNIDADE DE MEDIDA não foi INFORMADO.")
    private UnidadeMedidaResponse unidadeMedida;
    
    @NotNull(message = "O atributo MARCA não foi INFORMADO.")
    private MarcaResponse marca;

    public ProdutoCreateRequest() {
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
