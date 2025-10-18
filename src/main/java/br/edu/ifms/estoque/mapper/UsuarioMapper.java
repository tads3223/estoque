/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.mapper;

import br.edu.ifms.estoque.dto.UsuarioRegisterRequest;
import br.edu.ifms.estoque.dto.UsuarioResponse;
import br.edu.ifms.estoque.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author 1513003
 */
@Mapper(uses = { PerfilMapper.class })
public interface UsuarioMapper extends IMapper<Usuario, UsuarioResponse, UsuarioRegisterRequest, UsuarioRegisterRequest> {
    
    public UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(target = "bloqueado", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "perfis", ignore = true)
    @Override
    public Usuario toEntity(UsuarioRegisterRequest request);

    @Mapping(target = "bloqueado", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "perfis", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Override
    public Usuario update(UsuarioRegisterRequest request, @MappingTarget Usuario entity);
}
