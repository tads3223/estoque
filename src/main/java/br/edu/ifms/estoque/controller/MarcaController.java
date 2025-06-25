/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.controller;

import br.edu.ifms.estoque.dto.MarcaRequest;
import br.edu.ifms.estoque.dto.MarcaResponse;
import br.edu.ifms.estoque.exceptions.MarcaNotFoundException;
import br.edu.ifms.estoque.mapper.MarcaMapper;
import br.edu.ifms.estoque.model.Marca;
import br.edu.ifms.estoque.repository.MarcaRepository;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
@RestController
@RequestMapping("/marca")
public class MarcaController {

    private final MarcaRepository repository;
    private final MarcaMapper mapper;

    public MarcaController(MarcaRepository repository, MarcaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    
    @Transactional
    @PostMapping
    public ResponseEntity<MarcaResponse> create(
            @RequestBody @Valid MarcaRequest request,
            UriComponentsBuilder uriBuilder
    ) {
        var marca = mapper.toEntity(request);
        var saved = repository.save(marca);
        
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toDto(saved));
    }

    @GetMapping
    public List<MarcaResponse> list() {
        var lista = repository.findAll();
        return mapper.toListDto(lista);
    }

    /**
     * Registro da busca em uma lista ao invés de usar o STREAM.
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<MarcaResponse> findBy(
            @PathVariable Long id
    ) {
        var marca = find(id);
        
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toDto(marca));
    }

    public Marca find(
            Long id
    ) {
        return repository.findById(id)
                .orElseThrow(MarcaNotFoundException::new);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable(name = "id") Long id
    ) {
        var marca = find(id);
        repository.delete(marca);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<MarcaResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid MarcaRequest request
    ) {
        var entity = find(id);
        var updated = mapper.update(request, entity);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toDto(updated));
    }

}
