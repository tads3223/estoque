/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.arquitetura.controller;

import br.edu.ifms.estoque.arquitetura.dto.DefaultRequest;
import br.edu.ifms.estoque.arquitetura.dto.DefaultResponse;
import br.edu.ifms.estoque.arquitetura.mapper.IMapper;
import br.edu.ifms.estoque.arquitetura.service.IService;
import java.net.URI;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Adapter CompodedID. Adapter da controller utilizado para implementar endpoints cujas
 * entidades são gerenciadas por um ID (Chave primária ) Composta e que precisam
 * ser manipuladas no sistema. <br/>
 * Ela herda as características de <code>CRUDControllerAdapter</code> e
 * implementa do métodos <code>findById</code>, <code>delete</code> e
 * <code>update</code>.
 *
 * @author 1513003
 * @param <E> Classe que representa o objeto que será manipulado na controller
 * @param <K> Classe que representa a chave primária do objeto que será
 * manipulado pelos serviços
 * @param <DTO_RESPONSE> Subclasse DTO que herda da classe DefaultResponse e que
 * representa a resposta a ser encaminhada para o cliente requisitante
 * @param <DTO_CREATE_REQUEST> Classe DTO que herda da classe DefaulRequest e
 * que representa um objeto da classe DTO que contem os atributos para criação
 * de uma entidade no banco de dados do sistema
 * @param <DTO_UPDATE_REQUEST> Classe DTO que representa um objeto da classe DTO
 * que contem os atributos para alteração de dados de uma entidade no banco de
 * dados do sistema
 */
public abstract class CRUDDefaultControllerAdapter<E, K, DTO_RESPONSE extends DefaultResponse, DTO_CREATE_REQUEST extends DefaultRequest, DTO_UPDATE_REQUEST>
        extends CRUDControllerAdapterID<E, K, DTO_RESPONSE, DTO_CREATE_REQUEST, DTO_UPDATE_REQUEST>
        implements IController<K, DTO_RESPONSE, DTO_CREATE_REQUEST, DTO_UPDATE_REQUEST> {

    public CRUDDefaultControllerAdapter(
            IService<E, K, DTO_RESPONSE, DTO_CREATE_REQUEST, DTO_UPDATE_REQUEST> service,
            IMapper<E, DTO_RESPONSE, DTO_CREATE_REQUEST, DTO_UPDATE_REQUEST> mapper
    ) {
        super(service, mapper);
    }

    /**
     * Método para criar URI.Método utilizado para criar a URI que será
     * devolvida na resposta ao sistema requisitante.
     *
     * @param response Objeto da classe de resposta que contem o ID gerado no
     * banco de dados
     * @param uriBuilder Objeto da classe UriComponentsBuilder utilizado para
     * criar a URI.
     * @return
     */
    @Override
    protected URI createURI(DTO_RESPONSE response, UriComponentsBuilder uriBuilder) {
        return uriBuilder
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
    }

}
