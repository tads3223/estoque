/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifms.estoque.service;

import java.util.List;

/**
 *
 * @author 1513003
 * @param <E> Representa uma classe de Negócio, no pacote model
 * @param <K> Representa a classe da chave primária da classe <E>
 * @param <CREATE_DTO> Representa uma classe de DTO para requisição para criação de objetos no banco de dados, na pasta dto
 * @param <UPDATE_DTO> Representa uma classe de DTO para requisição para atualização de objetos no banco de dados, na pasta dto
 */
public interface IService<E, K, CREATE_DTO, UPDATE_DTO> {

    public E create(CREATE_DTO request);

    public E update(K id, UPDATE_DTO request);

    public void deleteById(K id);

    public E findBy(K id);

    public List<E> list();

}
