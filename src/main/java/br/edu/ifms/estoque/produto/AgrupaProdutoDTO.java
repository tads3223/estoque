/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.produto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author 1513003
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgrupaProdutoDTO {
    private int quantidadeSubgrupos;
    private Long grupoProdutoId;
    private String nome;
    
}
