/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.controller;

import br.edu.ifms.estoque.dto.DefaultRequest;
import br.edu.ifms.estoque.dto.DefaultResponse;
import br.edu.ifms.estoque.dto.MarcaResponse;
import br.edu.ifms.estoque.mapper.IMapper;
import br.edu.ifms.estoque.service.IService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author 1513003
 * @param <E>   Classe que representa o objeto que será manipulado na controller
 * @param <K> Classe que representa a chave primária do objeto que será
 * manipulado pelos serviços
 * @param <DTO_RESPONSE> Subclasse DTO que herda da classe DefaultResponse e que representa a resposta a ser encaminhada
 * para o cliente requisitante
 * @param <DTO_CREATE_REQUEST> Classe DTO que herda da classe DefaulRequest e que representa um objeto da classe DTO que
 * contem os atributos para criação de uma entidade no banco de dados do sistema
 * @param <DTO_UPDATE_REQUEST> Classe DTO que representa um objeto da classe DTO que
 * contem os atributos para alteração de dados de uma entidade no banco de dados do sistema
 */
public abstract class CRUDDefaultControllerAdapter<E, K, 
            DTO_RESPONSE extends DefaultResponse, 
            DTO_CREATE_REQUEST extends DefaultRequest, 
            DTO_UPDATE_REQUEST>
        extends CRUDControllerAdapter<E, K, 
            DTO_RESPONSE, DTO_CREATE_REQUEST, DTO_UPDATE_REQUEST>
        implements IController<K, DTO_RESPONSE, DTO_CREATE_REQUEST, DTO_UPDATE_REQUEST> {

    public CRUDDefaultControllerAdapter(IService service, IMapper mapper) {
        super(service, mapper);
    }

    /**
     * Método para criar URI.Método utilizado para criar a URI que será
     * devolvida na resposta ao sistema requisitante.
     *
     * @param response  Objeto da classe de resposta que contem o ID gerado no banco de dados
     * @param uriBuilder    Objeto da classe UriComponentsBuilder utilizado para criar a URI.
     * @return
     */
    @Override
    protected URI createURI(DTO_RESPONSE response, UriComponentsBuilder uriBuilder) {
        return uriBuilder
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
    }

    @Operation(summary = "Recurso responsável por recuperar um objeto cadastrado, pela chave primária")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Dados encontrados com sucesso",
                content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MarcaResponse.class)
                    )
                }
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Dados não encontrados",
                content = @Content
        )
    })
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<DTO_RESPONSE> findById(K id) {
        var entity = service.findBy(id);
        var response = mapper.toDto(entity);
        return okResponse(response);
    }

    @Operation(summary = "Recurso responsável por excluir um objeto cadastrado no sistema pela chave primária")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "204",
                description = "Dados removidos com sucesso",
                content = @Content
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Dados não encontrados",
                content = @Content
        ),
        @ApiResponse(
                responseCode = "409",
                description = "Não é possível deletar o objeto."
        )
    })
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(K id) {
        service.deleteById(id);
        return voidResponse();
    }

    @Operation(summary = "Recurso responsável por recuperar e alterar os dados de um objeto pela chave primária")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Dados alterados com sucesso",
                content = @Content
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Dados não encontrados",
                content = @Content
        )
    })
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<DTO_RESPONSE> update(K id, DTO_UPDATE_REQUEST request) {
        var entity = service.findBy(id);
        var updated = mapper.update(request, entity);
        var response = mapper.toDto(updated);
        return okResponse(response);
    }
    
}
