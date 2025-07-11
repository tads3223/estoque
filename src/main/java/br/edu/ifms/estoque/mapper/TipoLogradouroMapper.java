/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.mapper;

import br.edu.ifms.estoque.dto.TipoLogradouroRequest;
import br.edu.ifms.estoque.dto.TipoLogradouroResponse;
import br.edu.ifms.estoque.model.TipoLogradouro;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author 1513003
 */
@Component
public class TipoLogradouroMapper implements 
        IMapper<TipoLogradouro, TipoLogradouroResponse,
        TipoLogradouroRequest, TipoLogradouroRequest> {
    
    @Override
    public TipoLogradouro toEntity(
            TipoLogradouroRequest dto
    ) {
        var entity = new TipoLogradouro(
                dto.getNome(),
                dto.getSigla()
        );
        return entity;
    }
    
    @Override
    public TipoLogradouroResponse toDto(
            TipoLogradouro entity
    ) {
        TipoLogradouroResponse dto = new TipoLogradouroResponse(
                entity.getId(),
                entity.getNome(),
                entity.getSigla()
        );
        return dto;
    }
    
    @Override
    public List<TipoLogradouroResponse> toListDto(
            List<TipoLogradouro> list
    ) {
        return list.stream()
                .map((entity) -> toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public TipoLogradouro update(
            TipoLogradouroRequest request, 
            TipoLogradouro entity) {
        entity.setNome(request.getNome());
        entity.setSigla(request.getSigla());
        return entity;
    }
}
