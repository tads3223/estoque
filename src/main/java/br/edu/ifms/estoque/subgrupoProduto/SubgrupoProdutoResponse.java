/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.subgrupoProduto;

import br.edu.ifms.estoque.arquitetura.dto.DefaultResponse;

/**
 *
 * @author 1513003
 */
public class SubgrupoProdutoResponse extends DefaultResponse {
    
    private SubgrupoParent grupoProduto;

    public SubgrupoProdutoResponse() {
    }

    public SubgrupoProdutoResponse(Long id, String nome, SubgrupoParent grupoProduto) {
        super(id, nome);
        this.grupoProduto = grupoProduto;
    }

    public SubgrupoParent getGrupoProduto() {
        return grupoProduto;
    }
}
