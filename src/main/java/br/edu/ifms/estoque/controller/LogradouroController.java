/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.controller;

import br.edu.ifms.estoque.dto.LogradouroRequest;
import br.edu.ifms.estoque.dto.LogradouroResponse;
import br.edu.ifms.estoque.mapper.LogradouroMapper;
import br.edu.ifms.estoque.model.Logradouro;
import br.edu.ifms.estoque.repository.LogradouroRepository;
import br.edu.ifms.estoque.repository.TipoLogradouroRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 1513003
 */
@RestController
@RequestMapping("/logradouro")
public class LogradouroController {
    
    private final LogradouroRepository repository;
    
    private final TipoLogradouroRepository tipoLogradouroRepository;
    
    private final LogradouroMapper mapper;

    public LogradouroController(LogradouroRepository repository, TipoLogradouroRepository tipoLogradouroRepository, LogradouroMapper mapper) {
        this.repository = repository;
        this.tipoLogradouroRepository = tipoLogradouroRepository;
        this.mapper = mapper;
    }
    
    @Transactional
    @PostMapping
    public ResponseEntity<LogradouroResponse> create(
            @RequestBody LogradouroRequest request
    ) {
        var logradouro = mapper
                .toEntity(request, tipoLogradouroRepository);
        // Enfim, salva o objeto no banco de dados
        var saved = repository.save(logradouro);
        var dto = mapper.toDto(saved);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    
    @GetMapping
    public List<LogradouroResponse> list() {
        List<Logradouro> l = repository.findAll();
        return mapper.toListDto(l);
    }
    
    @GetMapping("/{id}")
    public Logradouro find(
            @PathVariable Long id
    ) {
        return repository.findById(id).get();
    }
    
    @Transactional
    @PutMapping("/{id}")
    public Logradouro update(
            @PathVariable Long id,
            @RequestBody Logradouro entity
    ) {
        var logradouro = repository.findById(id).get();
        logradouro.setNome(entity.getNome());
        logradouro.setTipoLogradouro(entity.getTipoLogradouro());
        return logradouro;
    }
    
}
