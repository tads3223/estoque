/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.usuario.mapper;

import br.edu.ifms.estoque.usuario.dto.UsuarioRegisterRequest;
import br.edu.ifms.estoque.usuario.dto.UsuarioResponse;
import br.edu.ifms.estoque.arquitetura.mapper.IMapper;
import br.edu.ifms.estoque.perfil.PerfilMapper;
import br.edu.ifms.estoque.usuario.dto.UsuarioCreateRequest;
import br.edu.ifms.estoque.usuario.dto.UsuarioUpdateRequest;
import br.edu.ifms.estoque.usuario.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author 1513003
 */
@Mapper(uses = { PerfilMapper.class })
public interface UsuarioMapper 
        extends IMapper<Usuario, UsuarioResponse, UsuarioCreateRequest, UsuarioUpdateRequest> {
    
    public UsuarioMapper INSTANCE = Mappers
            .getMapper(UsuarioMapper.class);
    
    @Mapping(target = "bloqueado", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "perfis", ignore = true)
    @Mapping(target = "authProvider", ignore = true)
    @Mapping(target = "senha", ignore = true)
    @Override
    public Usuario toEntity(UsuarioCreateRequest request);
    
    @Mapping(target = "bloqueado", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "perfis", ignore = true)
    @Mapping(target = "authProvider", ignore = true)
    public Usuario toEntity(UsuarioRegisterRequest request);

    @Mapping(target = "bloqueado", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "perfis", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "authProvider", ignore = true)
    @Mapping(target = "senha", ignore = true)
    @Mapping(target = "login", ignore = true)
    @Override
    public Usuario update(UsuarioUpdateRequest request, @MappingTarget Usuario entity);
}
