/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.controller;

import br.edu.ifms.estoque.dto.MarcaRequest;
import br.edu.ifms.estoque.mapper.IMapper;
import br.edu.ifms.estoque.service.IService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author 1513003
 * @param <E>   Classe que representa o objeto que será manipulado na controller
 * @param <K> Classe que representa a chave primária do objeto que será
 * manipulado pelos serviços
 * @param <DTO_RESPONSE> Classe DTO que representa a resposta a ser encaminhada
 * para o cliente requisitante
 * @param <DTO_CREATE_REQUEST> Classe DTO que representa um objeto da classe DTO que
 * contem os atributos para criação de uma entidade no banco de dados do sistema
 * @param <DTO_UPDATE_REQUEST> Classe DTO que representa um objeto da classe DTO que
 * contem os atributos para alteração de dados de uma entidade no banco de dados do sistema
 */
public abstract class CRUDControllerAdapter<E, K, DTO_RESPONSE, DTO_CREATE_REQUEST, DTO_UPDATE_REQUEST>
        implements IController<K, DTO_RESPONSE, DTO_CREATE_REQUEST, DTO_UPDATE_REQUEST> {

    protected final IService<E, K, DTO_RESPONSE, DTO_CREATE_REQUEST, DTO_UPDATE_REQUEST> service;
    protected final IMapper<E, DTO_RESPONSE, DTO_CREATE_REQUEST, DTO_UPDATE_REQUEST> mapper;

    public CRUDControllerAdapter(
            IService<E, K, DTO_RESPONSE, DTO_CREATE_REQUEST, DTO_UPDATE_REQUEST> service, 
            IMapper<E, DTO_RESPONSE, DTO_CREATE_REQUEST, DTO_UPDATE_REQUEST> mapper
    ) {
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * Método para criar URI.Método utilizado para criar a URI que será
     * devolvida na resposta ao sistema requisitante.
     *
     * @param response  Objeto da classe de resposta que contem o ID gerado no banco de dados
     * @param uriBuilder    Objeto da classe UriComponentsBuilder utilizado para criar a URI.
     * @return
     */
    protected abstract URI createURI(DTO_RESPONSE response, UriComponentsBuilder uriBuilder);

    @Operation(summary = "Recurso utilizado para cadastrar dados de um objeto no sistema")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "201",
                description = "Objeto criado com sucesso",
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
                description = "Falha na tentativa de criação do objeto. Tente novamente"
        )
    })
    @PostMapping
    @Override
    public ResponseEntity<DTO_RESPONSE> create(
            @RequestBody @Valid DTO_CREATE_REQUEST request,
            UriComponentsBuilder uriBuilder) {
        var entity = service.create(request);
        DTO_RESPONSE response = mapper.toDto(entity);
        URI uri = createURI(response, uriBuilder);
        return createdResponse(response, uri);
    }

    @Operation(summary = "Recurso responsável por recuperar uma lista de objetos cadastrados no sistema")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Lista dos objetos encontrados",
                content = @Content
        )
    })
    @GetMapping
    @Override
    public ResponseEntity<List<DTO_RESPONSE>> list() {
        var items = service.list();
        var dtoItems = mapper.toListDto(items);
        return okListResponse(dtoItems);
    }
    
}
