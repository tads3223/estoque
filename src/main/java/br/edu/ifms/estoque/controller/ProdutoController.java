/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.controller;

import br.edu.ifms.estoque.dto.ProdutoRequest;
import br.edu.ifms.estoque.dto.ProdutoResponse;
import br.edu.ifms.estoque.mapper.ProdutoMapper;
import br.edu.ifms.estoque.model.Produto;
import br.edu.ifms.estoque.repository.MarcaRepository;
import br.edu.ifms.estoque.repository.ProdutoRepository;
import br.edu.ifms.estoque.repository.SubgrupoProdutoRepository;
import br.edu.ifms.estoque.repository.UnidadeMedidaRepository;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 1513003
 */
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;
    
    @Autowired
    private SubgrupoProdutoRepository subgrupoRepository;
    
    @Autowired
    private UnidadeMedidaRepository unidadeMedidaRepository;
    
    @Autowired
    private MarcaRepository marcaRepository;
    
    @Transactional
    @PostMapping
    public ProdutoResponse create(
            @RequestBody @Valid ProdutoRequest request
    ) {
        var entity = ProdutoMapper
                .toEntity(request, subgrupoRepository,
                        unidadeMedidaRepository, marcaRepository);
        // Enfim, salva o objeto no banco de dados
        var saved = repository.save(entity);
        var dto = ProdutoMapper.toDto(saved);
        return dto;
    }
    
    @GetMapping
    public List<ProdutoResponse> list() {
        List<Produto> l = repository.findAll();
        return ProdutoMapper.listDto(l);
    }
    
    @GetMapping("/{id}")
    public Produto find(
            @PathVariable Long id
    ) {
        return repository.findById(id).get();
    }
    
//    @Transactional
//    @PutMapping("/{id}")
//    public Produto update(
//            @PathVariable Long id,
//            @RequestBody Produto entity
//    ) {
//        var logradouro = repository.findById(id).get();
//        logradouro.setNome(entity.getNome());
//        logradouro.setTipoProduto(entity.getTipoProduto());
//        return logradouro;
//    }
}
