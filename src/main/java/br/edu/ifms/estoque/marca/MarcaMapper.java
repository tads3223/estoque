/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.marca;

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
public interface MarcaMapper extends IMapper<Marca, MarcaResponse, MarcaRequest, MarcaRequest> {

    public MarcaMapper INSTANCE = Mappers.getMapper(MarcaMapper.class);

    @Mapping(target = "id", ignore = true)
    @Override
    public Marca toEntity(MarcaRequest request);
    
    @Mapping(target = "id", ignore = true)
    @Override
    public Marca update(
            MarcaRequest request, 
            @MappingTarget Marca entity);
    
    
}
