/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.controller;

import br.edu.ifms.estoque.dto.BairroResponse;
import br.edu.ifms.estoque.dto.BairroRequest;
import br.edu.ifms.estoque.mapper.BairroMapper;
import br.edu.ifms.estoque.model.Bairro;
import br.edu.ifms.estoque.service.BairroService;
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
@Tag(name = "bairro", description = "Recurso de controle de Bairros")
@RestController
@RequestMapping("/bairro")
public class BairroController 
        extends CRUDDefaultControllerAdapter<Bairro, Long, BairroResponse, BairroRequest, BairroRequest> {

    public BairroController(BairroService service, BairroMapper mapper) {
        super(service, mapper);
    }
//
//    @Operation(summary = "Recurso utilizado para cadastrar um bairro no sistema")
//    @ApiResponses(value = {
//        @ApiResponse(
//                responseCode = "201",
//                description = "Bairro criado com sucesso",
//                content = {
//                    @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = BairroRequest.class))
//                }
//        ),
//        @ApiResponse(
//                responseCode = "400",
//                description = "Um ou mais dados informados são inválidos ou estão ausentes"
//        ),
//        @ApiResponse(
//                responseCode = "409",
//                description = "Falha na tentativa de criação de um bairro. Tente novamente"
//        )
//    })
//    @PostMapping
//    @Override
//    public ResponseEntity<BairroResponse> create(
//            @io.swagger.v3.oas.annotations.parameters.RequestBody(
//                    description = "Bairro a ser criado",
//                    required = true,
//                    content = @Content(
//                            mediaType = "application/json",
//                            schema = @Schema(implementation = BairroRequest.class),
//                            examples = @ExampleObject(value = 
//                                    """
//                                    {
//                                        "nome": "Nome do novo bairro"
//                                    }
//                                    """)
//                    )
//            )
//            @RequestBody @Valid BairroRequest dto,
//            UriComponentsBuilder uriBuilder
//    ) {
//        return super.create(dto, uriBuilder);
//    }
//
//    @Operation(summary = "Recurso responsável por recuperar uma lista de bairros cadastrados no sistema")
//    @ApiResponses(value = {
//        @ApiResponse(
//                responseCode = "200",
//                description = "Lista de bairros encontrados",
//                content = @Content
//        )
//    })
//    @GetMapping
//    @Override
//    public ResponseEntity<List<BairroResponse>> list() {
//        return super.list();
//    }
//
//    @Operation(summary = "Recurso responsável por recuperar um bairro cadastrado pela chave primária")
//    @ApiResponses(value = {
//        @ApiResponse(
//                responseCode = "200",
//                description = "Bairro encontrado",
//                content = {
//                    @Content(
//                            mediaType = "application/json",
//                            schema = @Schema(implementation = BairroResponse.class)
//                    )
//                }
//        ),
//        @ApiResponse(
//                responseCode = "404",
//                description = "Bairro não encontrado",
//                content = @Content
//        )
//    })
//    @GetMapping("/{id}")
//    @Override
//    public ResponseEntity<BairroResponse> findById(
//            @Parameter(description = "ID do Bairro a ser buscado")
//            @PathVariable Long id
//    ) {
//        return super.findById(id);
//    }
//
//    @Operation(summary = "Recurso responsável por excluir um bairro cadastrado no sistema pela chave primária")
//    @ApiResponses(value = {
//        @ApiResponse(
//                responseCode = "204",
//                description = "Bairro removido com sucesso",
//                content = @Content
//        ),
//        @ApiResponse(
//                responseCode = "404",
//                description = "Bairro não encontrado",
//                content = @Content
//        ),
//        @ApiResponse(
//                responseCode = "409",
//                description = "Não é possível deletar um bairro."
//        )
//    })
//    @DeleteMapping("/{id}")
//    @Override
//    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
//        return super.delete(id);
//    }
//
//    @Operation(summary = "Recurso responsável por recuperar e alterar os dados de um bairro pela chave primária")
//    @ApiResponses(value = {
//        @ApiResponse(
//                responseCode = "200",
//                description = "Dados do bairro alterados com sucesso",
//                content = @Content
//        ),
//        @ApiResponse(
//                responseCode = "404",
//                description = "Bairro não encontrado",
//                content = @Content
//        )
//    })
//    @PutMapping("/{id}")
//    @Override
//    public ResponseEntity<BairroResponse> update(
//            @PathVariable Long id,
//            @RequestBody @Valid BairroRequest dto
//    ) {
//        return super.update(id, dto);
//    }

}
