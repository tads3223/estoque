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

    private final ProdutoRepository repository;
    private final SubgrupoProdutoRepository subgrupoRepository;
    private final UnidadeMedidaRepository unidadeMedidaRepository;
    private final MarcaRepository marcaRepository;
    private final ProdutoMapper mapper;

    public ProdutoController(ProdutoRepository repository, SubgrupoProdutoRepository subgrupoRepository, UnidadeMedidaRepository unidadeMedidaRepository, MarcaRepository marcaRepository, ProdutoMapper mapper) {
        this.repository = repository;
        this.subgrupoRepository = subgrupoRepository;
        this.unidadeMedidaRepository = unidadeMedidaRepository;
        this.marcaRepository = marcaRepository;
        this.mapper = mapper;
    }
    
    @Transactional
    @PostMapping
    public ProdutoResponse create(
            @RequestBody @Valid ProdutoRequest request
    ) {
        var entity = mapper
                .toEntity(request, subgrupoRepository,
                        unidadeMedidaRepository, marcaRepository);
        // Enfim, salva o objeto no banco de dados
        var saved = repository.save(entity);
        var dto = mapper.toDto(saved);
        return dto;
    }
    
    @GetMapping
    public List<ProdutoResponse> list() {
        List<Produto> l = repository.findAll();
        return mapper.toListDto(l);
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
