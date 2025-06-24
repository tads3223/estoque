/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

/**
 *
 * @author 1513003
 */
public class ProdutoRequest {

    @NotNull
    @NotEmpty
    @NotBlank
    private String nome;
    
    private String descricao;
    
    @NotNull(message = "O valor do estoque deve ser informado")
    @Min(value = 1, message = "O estoque mínimo é 1")
    private Integer estoqueMinimo;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "A data da última compra não foi informada")
    @PastOrPresent(message = "A data informada deve ser igual ou anterior a data atual.")
    private LocalDateTime dataUltimaCompra;
    
    @NotNull(message = "O atributo SUBGRUPO não foi INFORMADO.")
    private SubgrupoProdutoResponse subGrupo;
    
    @NotNull(message = "O atributo UNIDADE DE MEDIDA não foi INFORMADO.")
    private UnidadeMedidaResponse unidadeMedida;
    
    @NotNull(message = "O atributo MARCA não foi INFORMADO.")
    private MarcaResponse marca;

    public ProdutoRequest() {
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

    public LocalDateTime getDataUltimaCompra() {
        return dataUltimaCompra;
    }
}
