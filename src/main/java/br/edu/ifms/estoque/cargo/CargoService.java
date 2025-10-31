/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.cargo;

import br.edu.ifms.estoque.cargo.CargoRequest;
import br.edu.ifms.estoque.cargo.CargoResponse;
import br.edu.ifms.estoque.cargo.CargoNotFoundException;
import br.edu.ifms.estoque.cargo.CargoMapper;
import br.edu.ifms.estoque.cargo.Cargo;
import br.edu.ifms.estoque.cargo.CargoRepository;
import br.edu.ifms.estoque.arquitetura.service.IService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 1513003
 */
@Service
public class CargoService implements
        IService<Cargo, Long, CargoResponse, CargoRequest, CargoRequest> {

    private final CargoRepository repository;
    private final CargoMapper mapper;

    public CargoService(CargoRepository repository,
            CargoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public Cargo create(CargoRequest request) {
        Cargo marca = mapper.toEntity(request);
        Cargo created = repository.save(marca);
        return created;
    }

    @Override
    public Cargo update(Long id, CargoRequest request) {
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
    public Cargo findBy(Long id) {
        return repository.findById(id)
                .orElseThrow(CargoNotFoundException::new);
    }

    @Override
    public List<Cargo> list() {
        return repository.findAll();
    }

}
