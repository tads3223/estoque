/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.controller;

import br.edu.ifms.estoque.dto.IAgrupoProdutoDTO;
import br.edu.ifms.estoque.dto.SubgrupoProdutoResponse;
import br.edu.ifms.estoque.dto.SubgrupoProdutoRequest;
import br.edu.ifms.estoque.mapper.SubgrupoProdutoMapper;
import br.edu.ifms.estoque.service.SubgrupoProdutoService;
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
import org.springframework.http.HttpStatus;
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
@Tag(name = "subgrupo", description = "Recurso de controle de subgrupo de produtos")
@RestController
@RequestMapping("/subgrupo")
public class SubgrupoProdutoController 
        implements IController<Long, SubgrupoProdutoResponse, SubgrupoProdutoRequest, SubgrupoProdutoRequest> {

    private final SubgrupoProdutoService service;
    private final SubgrupoProdutoMapper mapper;

    public SubgrupoProdutoController(SubgrupoProdutoService service, SubgrupoProdutoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Cria um subgrupo de produto")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "201",
                description = "Subgrupo de produto criado com sucesso",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubgrupoProdutoRequest.class))
                }
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Um ou mais dados informados são inválidos ou estão ausentes"
        ),
        @ApiResponse(
                responseCode = "409",
                description = "Falha na tentativa de criação de um subgrupo de produto. Tente novamente"
        )
    })
    @PostMapping
    @Override
    public ResponseEntity<SubgrupoProdutoResponse> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Subgrupo de produto a ser criado",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SubgrupoProdutoRequest.class),
                            examples = @ExampleObject(value = 
                                    """
                                    {
                                        "nome": "Nome do novo subgrupo",
                                        "grupoProduto": {
                                            "id": "ID do subgrupo de produto superior a ser vinculado",
                                            "nome": "Nome do subgrupo que será vinculado"
                                        }
                                    }
                                    """)
                    )
            )
            @RequestBody @Valid SubgrupoProdutoRequest dto,
            UriComponentsBuilder uriBuilder
    ) {
        var entity = service.create(dto);
        URI uri = uriBuilder
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        var subgrupoResponse = mapper.toDto(entity);
        return createdResponse(subgrupoResponse, uri);
    }

    @Operation(summary = "Recupera a lista de subgrupos de produtos")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Lista de subgrupos de produtos encontrados",
                content = @Content
        )
    })
    @GetMapping
    @Override
    public ResponseEntity<List<SubgrupoProdutoResponse>> list() {
        var listDto = mapper.toListDto(service.list());
        return okListResponse(listDto);
    }

    @Operation(summary = "Recupera um subgrupo de produto pela chave primária")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Subgrupo de produto encontrado",
                content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SubgrupoProdutoResponse.class)
                    )
                }
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Subgrupo de produto não encontrado",
                content = @Content
        )
    })
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<SubgrupoProdutoResponse> findById(
            @Parameter(description = "ID do Subgrupo de produto a ser buscado")
            @PathVariable Long id
    ) {
        var dto = mapper.toDto(service.findBy(id));
        return okResponse(dto);
    }

    @Operation(summary = "Deleta um subgrupo de produto pela chave primária")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "204",
                description = "Subgrupo de produto removido com sucesso",
                content = @Content
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Subgrupo de produto não encontrado",
                content = @Content
        ),
        @ApiResponse(
                responseCode = "409",
                description = "Não é possível deletar um subgrupo de produto."
        )
    })
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
        return voidResponse();
    }

    @Operation(summary = "Altera os dados de um subgrupo de produto pela chave primária")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Dados do subgrupo de produto alterados com sucesso",
                content = @Content
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Subgrupo de produto não encontrado",
                content = @Content
        )
    })
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<SubgrupoProdutoResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid SubgrupoProdutoRequest dto
    ) {
        var entity = service.update(id, dto);
        var response = mapper.toDto(entity);
        return okResponse(response);
    }
    
    @GetMapping("/agrupa-produto")
    public ResponseEntity<List<IAgrupoProdutoDTO>> listaAgrupaSubgrupos() {
        var listaAgrupada = service.listaAgrupaSubgrupo();
        return ResponseEntity.status(HttpStatus.OK)
                .body(listaAgrupada);
    }

}
