/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.produto;

import br.edu.ifms.estoque.arquitetura.controller.CRUDDefaultControllerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 1513003
 */
@RestController
@RequestMapping("/api/produto")
public class ProdutoController
    extends CRUDDefaultControllerAdapter<Produto, Long, ProdutoResponse, ProdutoRequest, ProdutoRequest>{

    @Autowired
    public ProdutoController(
            ProdutoService service, 
            ProdutoMapper mapper) {
        super(service, mapper);
    }
}
