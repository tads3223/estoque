/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.unidadeMedida;

import br.edu.ifms.estoque.arquitetura.mapper.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author 1513003
 */
@Mapper
public interface UnidadeMedidaMapper extends 
        IMapper<UnidadeMedida, UnidadeMedidaResponse, UnidadeMedidaRequest, UnidadeMedidaRequest> {
    
    public UnidadeMedidaMapper INSTANCE = Mappers
            .getMapper(UnidadeMedidaMapper.class);

    @Mapping(target = "id", ignore = true)
    @Override
    public UnidadeMedida toEntity(UnidadeMedidaRequest request);

    
    @Mapping(target = "id", ignore = true)
    @Override
    public UnidadeMedida update(
            UnidadeMedidaRequest request,
            @MappingTarget UnidadeMedida entity);
}
