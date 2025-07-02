/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.controller;

import br.edu.ifms.estoque.dto.UnidadeMedidaResponse;
import br.edu.ifms.estoque.dto.UnidadeMedidaRequest;
import br.edu.ifms.estoque.mapper.UnidadeMedidaMapper;
import br.edu.ifms.estoque.service.UnidadeMedidaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
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
@Tag(name = "unidade_medida", description = "Recurso de controle de Unidades de Medidas")
@RestController
@RequestMapping("/unidade-medida")
public class UnidadeMedidaController 
        implements IController<Long, UnidadeMedidaResponse, UnidadeMedidaRequest, UnidadeMedidaRequest> {

    private final UnidadeMedidaService service;
    private final UnidadeMedidaMapper mapper;

    public UnidadeMedidaController(UnidadeMedidaService service, UnidadeMedidaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Cria uma unidade de medida")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "201",
                description = "Unidade de Medida criado com sucesso",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnidadeMedidaRequest.class))
                }
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Um ou mais dados informados são inválidos ou estão ausentes"
        ),
        @ApiResponse(
                responseCode = "409",
                description = "Falha na tentativa de criação de uma unidade de medida. Tente novamente"
        )
    })
    @PostMapping
    @Override
    public ResponseEntity<UnidadeMedidaResponse> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "UnidadeMedida a ser criado",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UnidadeMedidaRequest.class),
                            examples = @ExampleObject(value = 
                                    """
                                    {
                                        "nome": "Nome da nova unidade de medida"
                                    }
                                    """)
                    )
            )
            @RequestBody @Valid UnidadeMedidaRequest dto,
            UriComponentsBuilder uriBuilder
    ) {
        var entity = service.create(dto);
        URI uri = uriBuilder
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        var unidadeMedidaResponse = mapper.toDto(entity);
        return createdResponse(unidadeMedidaResponse, uri);
    }

    @Operation(summary = "Recupera a lista de unidades de medidas")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Lista de unidades de medidas encontrados",
                content = @Content
        )
    })
    @GetMapping
    @Override
    public ResponseEntity<List<UnidadeMedidaResponse>> list() {
        var listDto = mapper.toListDto(service.list());
        return okListResponse(listDto);
    }

    @Operation(summary = "Recupera uma unidade de medida pela chave primária")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Unidade de medida encontrada",
                content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UnidadeMedidaResponse.class)
                    )
                }
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Unidade de medida não encontrada",
                content = @Content
        )
    })
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<UnidadeMedidaResponse> findById(
            @Parameter(description = "ID da unidade de medida a ser buscada")
            @PathVariable Long id
    ) {
        var dto = mapper.toDto(service.findBy(id));
        return okResponse(dto);
    }

    @Operation(summary = "Deleta uma unidade de medida pela chave primária")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "204",
                description = "Unidade de medida removida com sucesso",
                content = @Content
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Unidade de medida não encontrada",
                content = @Content
        ),
        @ApiResponse(
                responseCode = "409",
                description = "Não é possível deletar uma unidade de medida."
        )
    })
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
        return voidResponse();
    }

    @Operation(summary = "Alterar os dados de uma unidade de medida pela chave primária")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Dados da unidade de medida alterados com sucesso",
                content = @Content
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Unidade de medida não encontrada",
                content = @Content
        )
    })
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<UnidadeMedidaResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid UnidadeMedidaRequest dto
    ) {
        var entity = service.update(id, dto);
        var response = mapper.toDto(entity);
        return okResponse(response);
    }

}
