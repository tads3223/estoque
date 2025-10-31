/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.subgrupoProduto;

import br.edu.ifms.estoque.arquitetura.controller.CRUDDefaultControllerAdapter;
import br.edu.ifms.estoque.produto.IAgrupoProdutoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author 1513003
 */
@Tag(name = "subgrupo", description = "Recurso de controle de subgrupo de produtos")
@RestController
@RequestMapping("/api/subgrupo")
public class SubgrupoProdutoController 
        extends CRUDDefaultControllerAdapter<SubgrupoProduto, Long, SubgrupoProdutoResponse, SubgrupoProdutoRequest, SubgrupoProdutoRequest> {

    public SubgrupoProdutoController(SubgrupoProdutoService service, SubgrupoProdutoMapper mapper) {
        super(service, mapper);
    }
    
    @GetMapping("/agrupa-produto")
    public ResponseEntity<List<IAgrupoProdutoDTO>> listaAgrupaSubgrupos() {
        var subgrupoService = (SubgrupoProdutoService)service;
        var listaAgrupada = subgrupoService.listaAgrupaSubgrupo();
        return ResponseEntity.status(HttpStatus.OK)
                .body(listaAgrupada);
    }

}
