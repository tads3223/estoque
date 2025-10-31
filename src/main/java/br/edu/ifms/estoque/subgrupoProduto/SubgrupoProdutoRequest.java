/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.subgrupoProduto;

import br.edu.ifms.estoque.arquitetura.dto.DefaultRequest;

/**
 *
 * @author 1513003
 */
public class SubgrupoProdutoRequest extends DefaultRequest {

    private SubgrupoParent grupoProduto;
    
    public SubgrupoProdutoRequest() {
    }

    public SubgrupoProdutoRequest(String nome, SubgrupoParent grupoProduto) {
        super(nome);
        this.grupoProduto = grupoProduto;
    }

    public SubgrupoParent getGrupoProduto() {
        return grupoProduto;
    }
    
}
