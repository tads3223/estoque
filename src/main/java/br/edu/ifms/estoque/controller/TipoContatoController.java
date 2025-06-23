/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.controller;

import br.edu.ifms.estoque.dto.TipoContatoRequest;
import br.edu.ifms.estoque.dto.TipoContatoResponse;
import br.edu.ifms.estoque.mapper.TipoContatoMapper;
import br.edu.ifms.estoque.service.TipoContatoService;
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
@RestController
@RequestMapping("/tipo-contato")
public class TipoContatoController {

    private final TipoContatoService service;
    private final TipoContatoMapper mapper;

    public TipoContatoController(TipoContatoService service, TipoContatoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<TipoContatoResponse>> list() {
        var items = mapper.toListDto(service.list());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoContatoResponse> find(
            @PathVariable Long id
    ) {
        var response = mapper.toDto(service.findBy(id));
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping
    public ResponseEntity<TipoContatoResponse> create(
            @RequestBody @Valid TipoContatoRequest request,
            UriComponentsBuilder uriBuilder
    ) {
        var saved = service.create(request);
        URI uri = uriBuilder
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(mapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoContatoResponse> update(
            @PathVariable Long id,
            @RequestBody TipoContatoRequest request
    ) {
        var entity = service.findBy(id);
        var updated = mapper.update(request, entity);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        service.deleteById(id);
        /**
         * retorna status 204 No Content, indicando que a exclusão foi
         * bem-sucedida e não há corpo na resposta.
         */
        return ResponseEntity.noContent().build();
    }
}
