/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.subgrupoProduto;

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
public interface SubgrupoProdutoMapper extends
        IMapper<SubgrupoProduto, SubgrupoProdutoResponse, SubgrupoProdutoRequest, SubgrupoProdutoRequest> {
    
    public SubgrupoProdutoMapper INSTANCE = Mappers
            .getMapper(SubgrupoProdutoMapper.class);

    public SubgrupoParentResponse toParentDto(SubgrupoProduto entity);
    
    @Mapping(target = "grupoProduto", ignore = true)
    public SubgrupoProduto toParentEntity(SubgrupoParentResponse dto);

    @Mapping(target = "id", ignore = true)
    @Override
    public SubgrupoProduto toEntity(SubgrupoProdutoRequest request);

    @Mapping(target = "id", ignore = true)
    @Override
    public SubgrupoProduto update(
            SubgrupoProdutoRequest request, 
            @MappingTarget SubgrupoProduto entity);
    
    
}
