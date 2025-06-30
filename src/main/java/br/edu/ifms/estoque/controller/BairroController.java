/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.controller;

import br.edu.ifms.estoque.dto.BairroResponse;
import br.edu.ifms.estoque.dto.BairroRequest;
import br.edu.ifms.estoque.mapper.BairroMapper;
import br.edu.ifms.estoque.service.BairroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
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
@Tag(name="bairro", description = "Recurso de controle de Bairros")
@RestController
@RequestMapping("/bairro")
public class BairroController implements IController<Long, BairroResponse, BairroRequest> {

    private final BairroService service;
    private final BairroMapper mapper;

    public BairroController(BairroService service, BairroMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    
    @PostMapping
    @Override
    public ResponseEntity<BairroResponse> create(
            @RequestBody @Valid BairroRequest dto,
            UriComponentsBuilder uriBuilder
    ) { 
        var entity = service.create(dto);
        URI uri = uriBuilder
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        var bairroResponse = mapper.toDto(entity);
        return createdResponse(bairroResponse, uri);
    }

    @Operation(summary = "Recupera a lista de Bairros")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Lista de bairros encontrados",
                content = @Content
        )
    })
    @GetMapping
    @Override
    public ResponseEntity<List<BairroResponse>> list() {
        var listDto = mapper.toListDto(service.list());
        return okListResponse(listDto);
    }

    @Operation(summary = "Recupera um Bairro pela chave primária")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Bairro encontrado",
                content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BairroResponse.class)
                    )
                }
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Bairro não encontrado",
                content = @Content
        )
    })
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<BairroResponse> findById(
            @Parameter(description = "ID do Bairro a ser buscado")
            @PathVariable Long id
    ) {
        var dto = mapper.toDto(service.findBy(id));
        return okResponse(dto);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
        return voidResponse();
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<BairroResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid BairroRequest dto
    ) {
        var entity = service.update(id, dto);
        var response = mapper.toDto(entity);
        return okResponse(response);
    }

}
