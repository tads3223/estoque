/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.controller;

import br.edu.ifms.estoque.dto.BairroReponse;
import br.edu.ifms.estoque.dto.BairroRequest;
import br.edu.ifms.estoque.model.Bairro;
import br.edu.ifms.estoque.repository.BairroRepository;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
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
@RequestMapping("/bairro")
public class BairroController {

    @Autowired
    private BairroRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity<BairroReponse> create(
            @RequestBody @Valid BairroRequest dto
    ) { 
        Bairro bairro = new Bairro(null, dto.getNome());
        repository.save(bairro);
        
        var bairroResponse = new BairroReponse(
                bairro.getId(), 
                bairro.getNome()
        );
        
        return new ResponseEntity(
                bairroResponse, 
                HttpStatus.OK
        );
    }

    @GetMapping
    public List<Bairro> list() {
        var lista = repository.findAll();
        return lista;
    }

    /**
     * Registro da busca em uma lista ao invés de usar o STREAM.
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Bairro findBy(
            @PathVariable Long id
    ) {
        var optional = find(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public Optional<Bairro> find(
            Long id
    ) {
        Optional<Bairro> optional = repository.findById(id);
        return optional;
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable(name = "id") Long id
    ) {
        var optional = find(id);
        if (optional.isPresent()) {
            Bairro tipoLogradouro = optional.get();
            repository.delete(tipoLogradouro);
        }
    }

    @Transactional
    @PutMapping("/{id}")
    public Bairro update(
            @PathVariable Long id,
            @RequestParam(name = "nome", required = true) String nome
    ) {
        var optional = find(id);
        if (optional.isPresent()) {
            var tipoLogradouro = optional.get();
            tipoLogradouro.setNome(nome);
            return tipoLogradouro;
        }
        return null;
    }

}
