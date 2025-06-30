/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.service;

import br.edu.ifms.estoque.dto.SubgrupoProdutoRequest;
import br.edu.ifms.estoque.exceptions.SubgrupoNotExistException;
import br.edu.ifms.estoque.mapper.SubgrupoProdutoMapper;
import br.edu.ifms.estoque.model.SubgrupoProduto;
import br.edu.ifms.estoque.repository.SubgrupoProdutoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 1513003
 */
@Service
public class SubgrupoProdutoService implements IService<SubgrupoProduto, Long, SubgrupoProdutoRequest, SubgrupoProdutoRequest> {
    
    private final SubgrupoProdutoRepository repository;
    private final SubgrupoProdutoMapper mapper;

    public SubgrupoProdutoService(SubgrupoProdutoRepository repository, SubgrupoProdutoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public SubgrupoProduto create(SubgrupoProdutoRequest request) {
        var entity = mapper.toEntity(request);
        return repository.save(entity);
    }

    @Override
    public SubgrupoProduto update(Long id, SubgrupoProdutoRequest request) {
        return repository.findById(id)
                .map(entity -> mapper.update(request, entity))
                .orElseThrow(SubgrupoNotExistException::new);
    }

    @Override
    public void deleteById(Long id) {
        var entity = findBy(id);
        repository.delete(entity);
    }

    @Override
    public SubgrupoProduto findBy(Long id) {
        return repository.findById(id)
                .orElseThrow(SubgrupoNotExistException::new);
    }

    @Override
    public List<SubgrupoProduto> list() {
        return repository.findAll();
    }
    
}
