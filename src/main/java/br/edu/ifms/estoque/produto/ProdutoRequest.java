/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.produto;

import br.edu.ifms.estoque.arquitetura.dto.DefaultRequest;
import br.edu.ifms.estoque.subgrupoProduto.SubgrupoProdutoResponse;
import br.edu.ifms.estoque.unidadeMedida.UnidadeMedidaResponse;
import br.edu.ifms.estoque.marca.MarcaResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
public class ProdutoRequest extends DefaultRequest {

    private String descricao;

    @NotNull(message = "O valor do estoque deve ser informado")
    @Min(value = 1, message = "O estoque mínimo é 1")
    private Integer estoqueMinimo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "A data da última compra não foi informada")
    @PastOrPresent(message = "A data informada deve ser igual ou anterior a data atual.")
    private Instant dataUltimaCompra;

    @NotNull(message = "O atributo SUBGRUPO não foi INFORMADO.")
    private SubgrupoProdutoResponse subgrupo;

    @NotNull(message = "O atributo UNIDADE DE MEDIDA não foi INFORMADO.")
    private UnidadeMedidaResponse unidadeMedida;

    @NotNull(message = "O atributo MARCA não foi INFORMADO.")
    private MarcaResponse marca;
}
