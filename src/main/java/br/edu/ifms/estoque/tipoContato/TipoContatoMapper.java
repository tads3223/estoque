/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.tipoContato;

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
public interface TipoContatoMapper extends
        IMapper<TipoContato, TipoContatoResponse, TipoContatoRequest, TipoContatoRequest> {

    public TipoContatoMapper INSTANCE = Mappers
            .getMapper(TipoContatoMapper.class);
    
    @Mapping(target = "id", ignore = true)
    @Override
    public TipoContato toEntity(TipoContatoRequest request);

    @Mapping(target = "id", ignore = true)
    @Override
    public TipoContato update(TipoContatoRequest request, @MappingTarget TipoContato entity);

    
}
