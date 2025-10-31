/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.usuario.controller;

import br.edu.ifms.estoque.arquitetura.controller.IController;
import br.edu.ifms.estoque.usuario.dto.UsuarioCreateRequest;
import br.edu.ifms.estoque.usuario.dto.UsuarioRegisterRequest;
import br.edu.ifms.estoque.usuario.dto.UsuarioResponse;
import br.edu.ifms.estoque.usuario.dto.UsuarioUpdateRequest;
import br.edu.ifms.estoque.usuario.mapper.UsuarioMapper;
import br.edu.ifms.estoque.usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author 1513003
 */
@Tag(name = "usuario", description = "Recurso de controle de Usuários do Sistema")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuario")
public class UsuarioController 
        implements IController<String, UsuarioResponse, UsuarioCreateRequest, 
        UsuarioUpdateRequest> {
    
    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @Operation(summary = "Cria uma usuario")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "201",
                description = "Usuário criado com sucesso",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioRegisterRequest.class))
                }
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Um ou mais dados informados são inválidos ou estão ausentes"
        ),
        @ApiResponse(
                responseCode = "409",
                description = "Falha na tentativa de criação de um usuário. Tente novamente"
        )
    })
    @PostMapping
    @Override
    public ResponseEntity<UsuarioResponse> create(
            @RequestBody @Valid UsuarioCreateRequest request, 
            UriComponentsBuilder uriBuilder
    ) {
        var entity = service.create(request);
        URI uri = uriBuilder
                .path("/{login}")
                .buildAndExpand(entity.getLogin())
                .toUri();
        var unidadeMedidaResponse = mapper.toDto(entity);
        return createdResponse(unidadeMedidaResponse, uri);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<UsuarioResponse>> list() {
        var listDto = mapper.toListDto(service.list());
        return okListResponse(listDto);
    }

    @GetMapping("/{login}")
    @Override
    public ResponseEntity<UsuarioResponse> findById(
            @PathVariable String login
    ) {
        var dto = mapper.toDto(service.findBy(login));
        return okResponse(dto);
    }

    @PutMapping("/{login}")
    @Override
    public ResponseEntity<UsuarioResponse> update(
            @PathVariable String login,
            @RequestBody @Valid UsuarioUpdateRequest request) {
        var entity = service.update(login, request);
        var response = mapper.toDto(entity);
        return okResponse(response);
    }

    @DeleteMapping("/{login}")
    @Override
    public ResponseEntity<Void> delete(String login) {
        service.deleteById(login);
        return voidResponse();
    }
    
}
