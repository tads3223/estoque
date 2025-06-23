/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.service;

import br.edu.ifms.estoque.dto.TipoContatoRequest;
import br.edu.ifms.estoque.exceptions.TipoContatoNotFoundException;
import br.edu.ifms.estoque.mapper.TipoContatoMapper;
import br.edu.ifms.estoque.model.TipoContato;
import br.edu.ifms.estoque.repository.TipoContatoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author 1513003
 */
@Service
public class TipoContatoService 
        implements IService<TipoContato, Long, TipoContatoRequest, TipoContatoRequest> {

    private final TipoContatoMapper mapper;
    private final TipoContatoRepository repository;

    public TipoContatoService(TipoContatoRepository repository,
            TipoContatoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public TipoContato create(TipoContatoRequest request) {
        var entity = mapper.toEntity(request);
        return repository.save(entity);
    }

    @Transactional
    @Override
    public TipoContato update(Long id, TipoContatoRequest request) {
        return repository.findById(id)
                .map(element -> mapper.update(request, element))
                .orElseThrow(TipoContatoNotFoundException::new);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        var entity = findBy(id);
        repository.delete(entity);
    }

    @Override
    public TipoContato findBy(Long id) {
        return repository.findById(id)
                .orElseThrow(TipoContatoNotFoundException::new);
                
    }

    @Override
    public List<TipoContato> list() {
        return repository.findAll();
    }

}
