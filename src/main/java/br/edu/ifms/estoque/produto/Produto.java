/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.produto;

import br.edu.ifms.estoque.arquitetura.model.MappedBase;
import br.edu.ifms.estoque.marca.Marca;
import br.edu.ifms.estoque.subgrupoProduto.SubgrupoProduto;
import br.edu.ifms.estoque.unidadeMedida.UnidadeMedida;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author 1513003
 */
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@SequenceGenerator(sequenceName = "produto_sequence", name = "mappedSequence", allocationSize = 1)
public class Produto extends MappedBase {

    @ToString.Include
    private String descricao;
    
    @ToString.Include
    private BigDecimal preco;
    private BigDecimal estoque;
    private Instant dataUltimaCompra;
    
    @ToString.Include
    private Integer estoqueMinimo;

    @ManyToOne(optional = false)
    private SubgrupoProduto subgrupo;

    @ManyToOne(optional = false)
    private Marca marca;

    @ManyToOne(optional = false)
    private UnidadeMedida unidadeMedida;

}
