/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.usuario.controller;

import br.edu.ifms.estoque.arquitetura.controller.CRUDControllerAdapterID;
import br.edu.ifms.estoque.usuario.dto.UsuarioCreateRequest;
import br.edu.ifms.estoque.usuario.dto.UsuarioResponse;
import br.edu.ifms.estoque.usuario.dto.UsuarioUpdateRequest;
import br.edu.ifms.estoque.usuario.mapper.UsuarioMapper;
import br.edu.ifms.estoque.usuario.model.Usuario;
import br.edu.ifms.estoque.usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author 1513003
 */
@Tag(name = "usuario", description = "Recurso de controle de Usuários do Sistema")
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController 
        extends CRUDControllerAdapterID<
        Usuario, String, UsuarioResponse, 
        UsuarioCreateRequest, 
        UsuarioUpdateRequest> {

    @Autowired
    public UsuarioController(
            UsuarioService service, 
            UsuarioMapper mapper) {
        super(service, mapper);
    }

    @Override
    protected URI createURI(UsuarioResponse response, UriComponentsBuilder uriBuilder) {
        return uriBuilder
                .path("/{id}")
                .buildAndExpand(response.getLogin())
                .toUri();
    }
    
}
