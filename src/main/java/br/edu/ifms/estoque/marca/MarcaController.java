/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.marca;

import br.edu.ifms.estoque.arquitetura.controller.IController;
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
@Tag(name = "marca", description = "Recurso de controle de marcas de produtos")
@RestController
@RequestMapping("/marca")
public class MarcaController implements 
        IController<Long, MarcaResponse, MarcaRequest, MarcaRequest> {

    private final MarcaService service;
    private final MarcaMapper mapper;

    public MarcaController(MarcaService service, MarcaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Recurso utilizado para cadastrar um marca no sistema")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "201",
                description = "Marca criado com sucesso",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MarcaRequest.class))
                }
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Um ou mais dados informados são inválidos ou estão ausentes"
        ),
        @ApiResponse(
                responseCode = "409",
                description = "Falha na tentativa de criação de um marca. Tente novamente"
        )
    })
    @PostMapping
    @Override
    public ResponseEntity<MarcaResponse> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Marca a ser criado",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MarcaRequest.class),
                            examples = @ExampleObject(value = 
                                    """
                                    {
                                        "nome": "Nome da nova marca"
                                    }
                                    """)
                    )
            )
            @RequestBody @Valid MarcaRequest dto,
            UriComponentsBuilder uriBuilder
    ) {
        var entity = service.create(dto);
        URI uri = uriBuilder
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        var marcaResponse = mapper.toDto(entity);
        return createdResponse(marcaResponse, uri);
    }

    @Operation(summary = "Recurso responsável por recuperar uma lista de marcas cadastrados no sistema")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Lista de marcas encontrados",
                content = @Content
        )
    })
    @GetMapping
    @Override
    public ResponseEntity<List<MarcaResponse>> list() {
        var listDto = mapper.toListDto(service.list());
        return okListResponse(listDto);
    }

    @Operation(summary = "Recurso responsável por recuperar um marca cadastrado pela chave primária")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Marca encontrado",
                content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MarcaResponse.class)
                    )
                }
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Marca não encontrado",
                content = @Content
        )
    })
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<MarcaResponse> findById(
            @Parameter(description = "ID do Marca a ser buscado")
            @PathVariable Long id
    ) {
        var dto = mapper.toDto(service.findBy(id));
        return okResponse(dto);
    }

    @Operation(summary = "Recurso responsável por excluir um marca cadastrado no sistema pela chave primária")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "204",
                description = "Marca removido com sucesso",
                content = @Content
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Marca não encontrado",
                content = @Content
        ),
        @ApiResponse(
                responseCode = "409",
                description = "Não é possível deletar um marca."
        )
    })
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
        return voidResponse();
    }

    @Operation(summary = "Recurso responsável por recuperar e alterar os dados de um marca pela chave primária")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Dados do marca alterados com sucesso",
                content = @Content
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Marca não encontrado",
                content = @Content
        )
    })
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<MarcaResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid MarcaRequest dto
    ) {
        var entity = service.update(id, dto);
        var response = mapper.toDto(entity);
        return okResponse(response);
    }

}
