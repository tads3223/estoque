/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.controller;

import br.edu.ifms.estoque.dto.TipoLogradouroRequest;
import br.edu.ifms.estoque.dto.TipoLogradouroResponse;
import br.edu.ifms.estoque.mapper.TipoLogradouroMapper;
import br.edu.ifms.estoque.service.TipoLogradouroService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 1513003
 */
@RestController
@RequestMapping("/tipo-logradouro")
public class TipoLogradouroController {

    @Autowired
    private TipoLogradouroService service;
    
    @Autowired
    private TipoLogradouroMapper mapper;

    @Transactional
    @PostMapping
    public ResponseEntity<TipoLogradouroResponse> create(
            @RequestBody @Valid TipoLogradouroRequest dto
    ) {
        var saved = service.create(dto);
        var savedDto = mapper.toDto(saved);
        return new ResponseEntity(savedDto, HttpStatus.CREATED);
    }

    @GetMapping
    public List<TipoLogradouroResponse> list() {
        var lista = service.list();
        
        return mapper.toListDto(lista);
    }
    
    @GetMapping("/nome")
    public List<TipoLogradouroResponse> list(
            @RequestParam String nome
    ) {
        var lista = service.listarPorNome(nome);
        return mapper.toListDto(lista);
    }

    /**
     * Registro da busca em uma lista ao invés de usar o STREAM.
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<TipoLogradouroResponse> findBy(
            @PathVariable Long id
    ) {
        var entity = service.findBy(id);
        var dto = mapper.toDto(entity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable(name = "id") Long id
    ) {
        service.deleteById(id);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<TipoLogradouroResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid TipoLogradouroRequest request
    ) {
        var updated = service.update(id, request);
        var response = mapper.toDto(updated);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

}
