/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.arquitetura.controller;

import br.edu.ifms.estoque.arquitetura.mapper.IMapper;
import br.edu.ifms.estoque.arquitetura.service.IService;
import br.edu.ifms.estoque.marca.MarcaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Adapter ID. Adapter da controller utilizado para implementar endpoints cujas
 * entidades são gerenciadas por um ID (Chave primária) Simples e que precisam
 * ser manipuladas no sistema. <br/>
 * Ela herda as características de <code>CRUDControllerAdapter</code> e
 * implementa do métodos <code>findById</code>, <code>delete</code> e <code>update</code>.
 *
 * @author 1513003
 * @param <E> Classe que representa o objeto que será manipulado na controller
 * @param <K> Classe que representa a chave primária do objeto que será
 * manipulado pelos serviços
 * @param <DTO_RESPONSE> Classe DTO que representa a resposta a ser encaminhada
 * para o cliente requisitante
 * @param <DTO_CREATE_REQUEST> Classe DTO que representa um objeto da classe DTO
 * que contem os atributos para criação de uma entidade no banco de dados do
 * sistema
 * @param <DTO_UPDATE_REQUEST> Classe DTO que representa um objeto da classe DTO
 * que contem os atributos para alteração de dados de uma entidade no banco de
 * dados do sistema
 */
public abstract class CRUDControllerAdapterID<E, K, DTO_RESPONSE, DTO_CREATE_REQUEST, DTO_UPDATE_REQUEST>
        extends CRUDControllerAdapter<E, K, DTO_RESPONSE, DTO_CREATE_REQUEST, DTO_UPDATE_REQUEST>
        implements IController<K, DTO_RESPONSE, DTO_CREATE_REQUEST, DTO_UPDATE_REQUEST> {

    public CRUDControllerAdapterID(
            IService<E, K, DTO_RESPONSE, DTO_CREATE_REQUEST, DTO_UPDATE_REQUEST> service,
            IMapper<E, DTO_RESPONSE, DTO_CREATE_REQUEST, DTO_UPDATE_REQUEST> mapper
    ) {
        super(service, mapper);
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
    public ResponseEntity<DTO_RESPONSE> findById(
            @PathVariable K id
    ) {
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
    public ResponseEntity<Void> delete(
            @PathVariable K id
    ) {
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
    public ResponseEntity<DTO_RESPONSE> update(
            @PathVariable K id, 
            @RequestBody @Valid DTO_UPDATE_REQUEST request
    ){
        var entity = service.findBy(id);
        var updated = mapper.update(request, entity);
        var response = mapper.toDto(updated);
        return okResponse(response);
    }
}
