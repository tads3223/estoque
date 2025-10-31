/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.bairro;

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
public interface BairroMapper extends 
        IMapper<Bairro, BairroResponse, BairroRequest, BairroRequest> {

    public BairroMapper INSTANCE = Mappers
            .getMapper(BairroMapper.class);
    
    @Mapping(target = "id", ignore = true)
    @Override
    public Bairro toEntity(BairroRequest request);
    
    @Mapping(target = "id", ignore = true)
    @Override
    public Bairro update(
            BairroRequest request, 
            @MappingTarget Bairro entity);
    
}
