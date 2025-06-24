/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.mapper;

import br.edu.ifms.estoque.dto.CargoRequest;
import br.edu.ifms.estoque.dto.CargoResponse;
import br.edu.ifms.estoque.model.Cargo;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author 1513003
 */
public class CargoMapper implements 
        IMapper<Cargo, CargoResponse, CargoRequest, CargoRequest> {

    @Override
    public CargoResponse toDto(Cargo entity) {
        return new CargoResponse(
                entity.getId(), 
                entity.getNome()
        );
    }

    @Override
    public Cargo toEntity(CargoRequest request) {
        return new Cargo(null, request.getNome());
    }

    @Override
    public Cargo update(CargoRequest request, Cargo entity) {
        entity.setNome(request.getNome());
        return entity;
    }

    @Override
    public List<CargoResponse> toListDto(List<Cargo> items) {
        return items.stream()
                .map(item -> toDto(item))
                .collect(Collectors.toList());
    }
    
}
