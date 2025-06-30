/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.service;

import br.edu.ifms.estoque.dto.BairroRequest;
import br.edu.ifms.estoque.exceptions.BairroNotFoundException;
import br.edu.ifms.estoque.mapper.BairroMapper;
import br.edu.ifms.estoque.model.Bairro;
import br.edu.ifms.estoque.repository.BairroRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 1513003
 */
@Service
public class BairroService implements IService<Bairro, Long, BairroRequest, BairroRequest> {

    private final BairroRepository repository;
    private final BairroMapper mapper;

    public BairroService(BairroRepository repository, BairroMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    
    @Transactional
    @Override
    public Bairro create(BairroRequest request) {
        var entity = mapper.toEntity(request);
        return repository.save(entity);
    }

    @Transactional
    @Override
    public Bairro update(Long id, BairroRequest request) {
        return repository.findById(id)
                .map(entity -> mapper.update(request, entity))
                .orElseThrow(BairroNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        var entity = findBy(id);
        repository.delete(entity);
    }

    @Override
    public Bairro findBy(Long id) {
        return repository.findById(id)
                .orElseThrow(BairroNotFoundException::new);
    }

    @Override
    public List<Bairro> list() {
        return repository.findAll();
    }
    
}
