/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.controller;

import br.edu.ifms.estoque.model.Marca;
import br.edu.ifms.estoque.repository.MarcaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 1513003
 */
@RestController
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private MarcaRepository repository;

    @Transactional
    @PostMapping
    public Marca create(
            @RequestParam(name = "nome", required = true) String nome
    ) {
        var marca = new Marca(null, nome);
        repository.save(marca);
        
        return marca;
    }

    @GetMapping
    public List<Marca> list() {
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
    public Marca findBy(
            @PathVariable Long id
    ) {
        var optional = find(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public Optional<Marca> find(
            Long id
    ) {
        Optional<Marca> optional = repository.findById(id);
        return optional;
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable(name = "id") Long id
    ) {
        var optional = find(id);
        if (optional.isPresent()) {
            Marca tipoLogradouro = optional.get();
            repository.delete(tipoLogradouro);
        }
    }

    @Transactional
    @PutMapping("/{id}")
    public Marca update(
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
