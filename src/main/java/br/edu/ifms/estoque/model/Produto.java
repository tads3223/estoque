/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import br.edu.ifms.estoque.model.heranca.TablePerClassBase;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author 1513003
 */
@Entity
@SequenceGenerator(
        sequenceName = "produto_sequence",
        name = "tablePerClassBase",
        allocationSize = 1
)
public class Produto extends TablePerClassBase {

    private String descricao;
    private BigDecimal preco;
    private BigDecimal estoque;
    private LocalDate dataUltimaCompra;
    private Integer estoqueMinimo;

    @ManyToOne(optional = false)
    private SubgrupoProduto subgrupo;
    
    @ManyToOne(optional = false)
    private Marca marca;
    
    @ManyToOne(optional = false)
    private UnidadeMedida unidadeMedida;

    public Produto() {
    }

    public Produto(Long id, String nome) {
        super(id, nome);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public Integer getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(Integer estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public SubgrupoProduto getSubgrupo() {
        return subgrupo;
    }

    public void setSubgrupo(SubgrupoProduto subgrupo) {
        this.subgrupo = subgrupo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }
}
