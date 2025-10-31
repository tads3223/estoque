/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifms.estoque.arquitetura.controller;

import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author 1513003
 * @param <DTO_K> Classe que representa a chave primária do objeto que será
 * manipulado pelos serviços
 * @param <DTO_RESPONSE> Classe DTO que representa a resposta a ser encaminhada
 * para o cliente requisitante
 * @param <DTO_CREATE_REQUEST> Classe DTO que representa um objeto da classe DTO que
 * contem os atributos para criação de uma entidade no banco de dados do sistema
 * @param <DTO_UPDATE_REQUEST> Classe DTO que representa um objeto da classe DTO que
 * contem os atributos para alteração de dados de uma entidade no banco de dados do sistema
 */
public interface IController<DTO_K, DTO_RESPONSE, DTO_CREATE_REQUEST, DTO_UPDATE_REQUEST> {

    /**
     * Método para criar objetos.Método utilizado para interceptar a requisição
     * de serviços de criação de objetos no banco de dados.
     *
     * @param request Objeto que contem os dados que serão criados no sistema.
     * @param uriBuilder Objeto utilizado para criar a resposta 201 e um link de
     * acesso ao objeto após sua criação.
     * @return
     */
    public ResponseEntity<DTO_RESPONSE> create(
            DTO_CREATE_REQUEST request,
            UriComponentsBuilder uriBuilder);

    /**
     * Método para listar objetos. Método para interceptar a requisição de
     * serviços para listagem se dados.
     *
     * @return
     */
    public ResponseEntity<List<DTO_RESPONSE>> list();

    /**
     * Método para buscar um objeto. Método utilizado para interceptar a
     * requisição de serviço para buscar um objeto pela sua chave primária
     *
     * @param id Objeto que represente a chave primária
     * @return
     */
    public ResponseEntity<DTO_RESPONSE> findById(DTO_K id);

    /**
     * Método para alterar dados de um objeto. Método utilizado para interceptar
     * a requisição de serviço para alteração de dados de um objeto de acordo
     * com sua chave primária.
     *
     * @param id Objeto que represente a chave primária
     * @param request Objeto que contem os dados que serão alterados no sistema.
     * @return
     */
    public ResponseEntity<DTO_RESPONSE> update(DTO_K id, DTO_UPDATE_REQUEST request);

    /**
     * Método para excluir um objeto. Método utilizado para interceptar a
     * requisição de serviço para remover um objeto do sistema pela sua chave
     * primária.
     *
     * @param id
     * @return
     */
    public ResponseEntity<Void> delete(DTO_K id);

    public default ResponseEntity<DTO_RESPONSE> okResponse(DTO_RESPONSE dto) {
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    public default ResponseEntity<List<DTO_RESPONSE>> okListResponse(List<DTO_RESPONSE> items) {
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

    public default ResponseEntity<DTO_RESPONSE> createdResponse(
            DTO_RESPONSE dto,URI uri) {
        return ResponseEntity.created(uri).body(dto);
    }

    public default ResponseEntity<Void> voidResponse() {
        return ResponseEntity.noContent().build();
    }
}
