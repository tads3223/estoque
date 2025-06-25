/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.mapper;

import br.edu.ifms.estoque.dto.UnidadeMedidaRequest;
import br.edu.ifms.estoque.dto.UnidadeMedidaResponse;
import br.edu.ifms.estoque.model.UnidadeMedida;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author 1513003
 */
@Component
public class UnidadeMedidaMapper implements IMapper<UnidadeMedida, UnidadeMedidaResponse, UnidadeMedidaRequest, UnidadeMedidaRequest> {
    
    @Override
    public UnidadeMedidaResponse toDto(
            UnidadeMedida entity
    ) {
        UnidadeMedidaResponse dto = new UnidadeMedidaResponse(
                entity.getId(),
                entity.getNome(),
                entity.getFracionado()
        );
        return dto;
    }
    
    @Override
    public List<UnidadeMedidaResponse> toListDto(
            List<UnidadeMedida> list
    ) {
        return list.stream()
                .map((entity) -> toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public UnidadeMedida toEntity(UnidadeMedidaRequest request) {
        return new UnidadeMedida(
                null,
                request.getNome(),
                request.getFracionado()
        );
    }
    
    public UnidadeMedida toEntity(UnidadeMedidaResponse response) {
        return new UnidadeMedida(
                response.getId(),
                response.getNome(),
                response.getFracionado()
        );
    }

    @Override
    public UnidadeMedida update(UnidadeMedidaRequest request, UnidadeMedida entity) {
        entity.setNome(request.getNome());
        entity.setFracionado(request.getFracionado());
        return entity;
    }
}
