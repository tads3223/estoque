/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.bairro;

import br.edu.ifms.estoque.bairro.BairroRequest;
import br.edu.ifms.estoque.bairro.BairroResponse;
import br.edu.ifms.estoque.arquitetura.mapper.IMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author 1513003
 */
@Component
public class BairroMapper implements 
        IMapper<Bairro, BairroResponse, BairroRequest, BairroRequest> {

    @Override
    public BairroResponse toDto(Bairro entity) {
        return BairroResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .build();
    }

    @Override
    public Bairro toEntity(BairroRequest request) {
        return Bairro.builder()
                .nome(request.getNome())
                .build();
    }

    @Override
    public Bairro update(BairroRequest request, Bairro entity) {
        entity.setNome(request.getNome());
        return entity;
    }

    @Override
    public List<BairroResponse> toListDto(List<Bairro> items) {
        return items.stream()
                .map(item -> toDto(item))
                .collect(Collectors.toList());
    }
    
}
