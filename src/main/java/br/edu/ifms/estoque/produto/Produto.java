/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.produto;

import br.edu.ifms.estoque.marca.Marca;
import br.edu.ifms.estoque.subgrupoProduto.SubgrupoProduto;
import br.edu.ifms.estoque.unidadeMedida.UnidadeMedida;
import br.edu.ifms.estoque.arquitetura.model.TablePerClassBase;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

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
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@SuperBuilder
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

}
