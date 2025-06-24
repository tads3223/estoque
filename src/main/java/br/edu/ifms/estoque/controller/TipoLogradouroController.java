/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.controller;

import br.edu.ifms.estoque.dto.TipoLogradouroRequest;
import br.edu.ifms.estoque.dto.TipoLogradouroResponse;
import br.edu.ifms.estoque.mapper.TipoLogradouroMapper;
import br.edu.ifms.estoque.model.TipoLogradouro;
import br.edu.ifms.estoque.repository.TipoLogradouroRepository;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 1513003
 */
@RestController
public class TipoLogradouroController {

    @Autowired
    private TipoLogradouroRepository repository;
    
    @Autowired
    private TipoLogradouroMapper mapper;

    @Transactional
    @RequestMapping(method = RequestMethod.POST, path = "/tipo-logradouro")
    public ResponseEntity<TipoLogradouroResponse> create(
            @RequestBody @Valid TipoLogradouroRequest dto
    ) {
        TipoLogradouro tipoLogradouro = mapper
                .toEntity(dto);
        var saved = repository.save(tipoLogradouro);
        var savedDto = mapper.toDto(saved);
        return new ResponseEntity(savedDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/tipo-logradouro")
    public List<TipoLogradouroResponse> list() {
        var lista = repository.findAll();
        
        return mapper.toListDto(lista);
    }

    /**
     * Registro da busca em uma lista ao invés de usar o STREAM.
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/tipo-logradouro/{id}")
    public TipoLogradouro findBy(
            @PathVariable Long id
    ) {
        var optional = find(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public Optional<TipoLogradouro> find(
            Long id
    ) {
        Optional<TipoLogradouro> optional = repository.findById(id);
        return optional;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/tipo-logradouro/{id}")
    public void delete(
            @PathVariable(name = "id") Long id
    ) {
        var optional = find(id);
        if (optional.isPresent()) {
            TipoLogradouro tipoLogradouro = optional.get();
            repository.delete(tipoLogradouro);
        }
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PUT, path = "/tipo-logradouro/{id}")
    public TipoLogradouro update(
            @PathVariable Long id,
            @RequestParam(name = "nome", required = true) String nome,
            @RequestParam(name = "sigla", required = true) String sigla
    ) {
        var optional = find(id);
        if (optional.isPresent()) {
            var tipoLogradouro = optional.get();
            tipoLogradouro.setNome(nome);
            tipoLogradouro.setSigla(sigla);
            return tipoLogradouro;
        }
        return null;
    }

}
