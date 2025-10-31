/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.produto;

import br.edu.ifms.estoque.arquitetura.dto.DefaultResponse;
import br.edu.ifms.estoque.subgrupoProduto.SubgrupoProdutoResponse;
import br.edu.ifms.estoque.unidadeMedida.UnidadeMedidaResponse;
import br.edu.ifms.estoque.marca.MarcaResponse;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author 1513003
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProdutoResponse extends DefaultResponse {
    
    private String descricao;
    private Integer estoqueMinimo;
    
    private BigDecimal preco;
    private BigDecimal estoque;
    private Instant dataUltimaCompra;
    
    private SubgrupoProdutoResponse subgrupo;
    private UnidadeMedidaResponse unidadeMedida;
    private MarcaResponse marca;
    
}
