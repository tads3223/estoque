/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.perfil;

import br.edu.ifms.estoque.arquitetura.mapper.IMapper;
import br.edu.ifms.estoque.perfil.PerfilResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author 1513003
 */
@Mapper
public interface PerfilMapper extends IMapper<Perfil, PerfilResponse, PerfilResponse, PerfilResponse> {
    
    public PerfilMapper INSTANCE = Mappers.getMapper(PerfilMapper.class);

    @Mapping(target = "usuarios", ignore = true)
    @Override
    public Perfil toEntity(PerfilResponse request);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuarios", ignore = true)
    @Override
    public Perfil update(PerfilResponse request, @MappingTarget Perfil entity);
}
