/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.tipoLogradouro;

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
public interface TipoLogradouroMapper extends
        IMapper<TipoLogradouro, TipoLogradouroResponse, TipoLogradouroRequest, TipoLogradouroRequest> {

    public TipoLogradouroMapper INSTANCE = Mappers
            .getMapper(TipoLogradouroMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "logradouros", ignore = true)
    @Override
    public TipoLogradouro toEntity(TipoLogradouroRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "logradouros", ignore = true)
    @Override
    public TipoLogradouro update(TipoLogradouroRequest request, @MappingTarget TipoLogradouro entity);

}
