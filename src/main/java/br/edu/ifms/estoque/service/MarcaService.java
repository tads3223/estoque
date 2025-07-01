/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.service;

import br.edu.ifms.estoque.dto.MarcaRequest;
import br.edu.ifms.estoque.exceptions.MarcaNotFoundException;
import br.edu.ifms.estoque.mapper.MarcaMapper;
import br.edu.ifms.estoque.model.Marca;
import br.edu.ifms.estoque.repository.MarcaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 1513003
 */
@Service
public class MarcaService implements
        IService<Marca, Long, MarcaRequest, MarcaRequest> {

    private final MarcaRepository repository;
    private final MarcaMapper mapper;

    public MarcaService(MarcaRepository repository,
            MarcaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public Marca create(MarcaRequest request) {
        Marca marca = mapper.toEntity(request);
        Marca created = repository.save(marca);
        return created;
    }

    @Override
    public Marca update(Long id, MarcaRequest request) {
        var marca = findBy(id);
        var updated = mapper.update(request, marca);
        return updated;
    }

    @Override
    public void deleteById(Long id) {
        var marca = findBy(id);
        repository.delete(marca);
    }

    @Override
    public Marca findBy(Long id) {
        return repository.findById(id)
                .orElseThrow(MarcaNotFoundException::new);
//                .orElseThrow(() -> {
//                    var ex = new MarcaNotFoundException();
//                    return ex;
//                });
//        Optional<Marca> optional = repository.findById(id);
//        if (optional.isPresent()) {
//            Marca marca = optional.get();
//            return marca;
//        }
//        throw new MarcaNotFoundException();

//            Marca marca = repository.getReferenceById(id);
//            if (marca != null) {
//                return marca;
//            }
    }

    @Override
    public List<Marca> list() {
        return repository.findAll();
    }

}
