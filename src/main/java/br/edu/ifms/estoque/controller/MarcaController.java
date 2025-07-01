/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.controller;

import br.edu.ifms.estoque.dto.MarcaRequest;
import br.edu.ifms.estoque.dto.MarcaResponse;
import br.edu.ifms.estoque.mapper.MarcaMapper;
import br.edu.ifms.estoque.service.MarcaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
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
@Tag(name = "marca", description = "Recurso de controle de marcas de produtos")
@RestController
@RequestMapping("/marca")
public class MarcaController {

    private final MarcaService service;
    private final MarcaMapper mapper;

    public MarcaController(
            MarcaService service, 
            MarcaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }
    
    @Operation(summary = "Recurso utilizado para cadastrar uma marca de produto no sistema")
    @PostMapping
    public ResponseEntity<MarcaResponse> create(
            @RequestBody @Valid MarcaRequest request,
            UriComponentsBuilder uriBuilder
    ) {
        var saved = service.create(request);
        URI uri = uriBuilder
                .path("/marca/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        
        return ResponseEntity
                .created(uri)
                .body(mapper.toDto(saved));
    }

    @Operation(summary = "Recurso responsável por recuperar uma lista de marcas de produtos cadastradas no sistema")
    @GetMapping
    public ResponseEntity<List<MarcaResponse>> list() {
        var lista = service.list();
        var listDto = mapper.toListDto(lista);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(listDto);
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
        var marca = service.findBy(id);
        
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toDto(marca));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable(name = "id") Long id
    ) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<MarcaResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid MarcaRequest request
    ) {
        var updated = service.update(id, request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toDto(updated));
    }

}
