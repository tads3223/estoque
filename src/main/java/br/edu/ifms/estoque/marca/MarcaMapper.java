/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.marca;

import br.edu.ifms.estoque.arquitetura.mapper.IMapper;
import br.edu.ifms.estoque.marca.MarcaRequest;
import br.edu.ifms.estoque.marca.MarcaResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author 1513003
 */
@Component
public class MarcaMapper implements IMapper<Marca, MarcaResponse, MarcaRequest, MarcaRequest> {
    
    @Override
    public MarcaResponse toDto(
            Marca entity
    ) {
        MarcaResponse dto = new MarcaResponse(
                entity.getId(),
                entity.getNome()
        );
        return dto;
    }
    
    @Override
    public List<MarcaResponse> toListDto(
            List<Marca> list
    ) {
        return list.stream()
                .map((entity) -> toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public Marca toEntity(MarcaRequest request) {
        return new Marca(
                null,
                request.getNome()
        );
    }

    public Marca toEntity(MarcaResponse response) {
        return new Marca(
                response.getId(),
                response.getNome()
        );
    }
    
    @Override
    public Marca update(MarcaRequest request, Marca entity) {
        entity.setNome(request.getNome());
        return entity;
    }
}
