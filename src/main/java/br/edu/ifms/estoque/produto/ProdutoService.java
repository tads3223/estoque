/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.produto;

import br.edu.ifms.estoque.arquitetura.service.ServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 1513003
 */
@Service
public class ProdutoService extends 
        ServiceAdapter<Produto, Long, ProdutoResponse, ProdutoRequest, ProdutoRequest>{

    @Autowired
    public ProdutoService(
            ProdutoRepository repository, 
            ProdutoMapper mapper) {
        super(repository, mapper);
    }
    
}
