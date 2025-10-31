/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifms.estoque.arquitetura.service;

import java.util.List;

/**
 *
 * @author 1513003
 * @param <E> Representa uma classe de Negócio, no pacote model
 * @param <K> Representa a classe da chave primária da classe <E>
 * @param <DTO_RESPONSE> Classe DTO que representa a resposta a ser encaminhada
 * para o cliente requisitante. É utilizada para mapear a classe do MAPPER
 * (classe responsável pelo mapeamento dos objetos entidades para DTOS).
 * @param <DTO_CREATE_REQUEST> Representa uma classe de DTO para requisição para
 * criação de objetos no banco de dados, na pasta dto
 * @param <DTO_UPDATE_REQUEST> Representa uma classe de DTO para requisição para
 * atualização de objetos no banco de dados, na pasta dto
 */
public interface IService<E, K, DTO_RESPONSE, DTO_CREATE_REQUEST, DTO_UPDATE_REQUEST> {

    /**
     * Create. Método utilizado para cadastrar um novo objeto no banco de dados.
     * Ele converte o objeto REQUEST em uma Entidade de banco de dados e salva.
     *
     * @param request Objeto que contém os dados que foram intermediados pela
     * controller e que deve ser convertidos para uma entidade de banco de dados
     * para serem cadastrados no banco de dados.
     * @return
     */
    public E create(DTO_CREATE_REQUEST request);

    /**
     * Update. Método utilizado para alterar os dados de um objeto. Com o ID
     * informado, o método busca o objeto a ser alterado e aplica as alterações
     * de acordo com os dados do objeto de requisição.
     *
     * @param id Valor do ID de chave primária do objeto o qual terá seus dados
     * alterados
     * @param request Objeto que contém os dados que foram intermediados pela
     * controller e que deve ser convertidos para uma entidade de banco de dados
     * para terem seus dados atualizados no banco de dados.
     * @return
     */
    public E update(K id, DTO_UPDATE_REQUEST request);

    /**
     * Delete. Método utilizado para excluir os dados do sistema de um objeto
     * por meio do seu ID.
     *
     * @param id Valor do ID que representa o objeto que será excluído do
     * sistema.
     */
    public void deleteById(K id);

    /**
     * FindBy. Método utilizado para recuperar os dados de um objeto de acordo
     * com o valor do ID.
     *
     * @param id Valor do ID do objeto a ser recuperado do bando de dados.
     * @return
     */
    public E findBy(K id);

    /**
     * List. Método responsável por recuperar uma lista de objetos do banco de
     * dados.
     *
     * @return
     */
    public List<E> list();

}
