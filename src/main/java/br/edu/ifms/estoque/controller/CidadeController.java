/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.controller;

import br.edu.ifms.estoque.model.Cidade;
import br.edu.ifms.estoque.repository.CidadeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/cidade")
public class CidadeController {
    
    @Autowired
    private CidadeRepository repository;
    
    @Transactional
    @PostMapping
    public Cidade create(
            @RequestParam(name = "nome", required = true) String nome,
            @RequestParam(name = "uf", required = true) String uf
    ) {
            // Depois, cria o objeto logradouro
        var cidade = new Cidade(null, nome, uf);
        // Enfim, salva o objeto no banco de dados
        repository.save(cidade);
        return cidade;
    }
    
    @GetMapping
    public List<Cidade> list() {
        return repository.findAll();
    }
    
    @GetMapping("/{id}")
    public Cidade find(
            @PathVariable Long id
    ) {
        return repository.findById(id).get();
    }
    
    @Transactional
    @PutMapping("/{id}")
    public Cidade update(
            @PathVariable Long id,
            @RequestBody Cidade entity
    ) {
        var cidade = repository.findById(id).get();
        cidade.setNome(entity.getNome());
        cidade.setUf(entity.getUf());
        return cidade;
    }
    
    @Transactional
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {
        var optional = repository.findById(id);
        if (optional.isPresent()) {
            var entity = optional.get();
            repository.delete(entity);
        }
    }
    
}
