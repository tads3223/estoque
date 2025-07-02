/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.service;

import br.edu.ifms.estoque.exceptions.ResourceNotFoundException;
import br.edu.ifms.estoque.mapper.IMapper;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe adaptadora que implementa os métodos da interface de serviços
 *
 * @author 1513003
 * @param <E> Representa uma classe de Negócio, no pacote model
 * @param <K> Representa a classe da chave primária da classe <E>
 * @param <DTO_RESPONSE> Classe DTO que representa a resposta a ser encaminhada
 * para o cliente requisitante. É utilizada para mapear a classe do MAPPER
 * (classe responsável pelo mapeamento dos objetos entidades para DTOS).
 * @param <CREATE_DTO> Representa uma classe de DTO para requisição para criação
 * de objetos no banco de dados, na pasta dto
 * @param <UPDATE_DTO> Representa uma classe de DTO para requisição para
 * atualização de objetos no banco de dados, na pasta dto
 */
public abstract class ServiceAdapter<E, K, DTO_RESPONSE, CREATE_DTO, UPDATE_DTO>
        implements IService<E, K, DTO_RESPONSE, CREATE_DTO, UPDATE_DTO> {

    protected final JpaRepository<E, K> repository;
    protected final IMapper<E, DTO_RESPONSE, CREATE_DTO, UPDATE_DTO> mapper;

    public ServiceAdapter(JpaRepository<E, K> repository, IMapper<E, DTO_RESPONSE, CREATE_DTO, UPDATE_DTO> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public E create(CREATE_DTO request) {
        var entity = mapper.toEntity(request);
        return repository.save(entity);
    }

    @Override
    public E update(K id, UPDATE_DTO request) {
        var entity = findBy(id);
        return mapper.update(request, entity);
    }

    @Override
    public E findBy(K id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Os dados do objeto não foram encontrados"));
    }

    /**
     * Delete. Método utilizado para excluir fisicamente os dados do sistema de
     * um objeto por meio do seu ID. Caso deseje implementar uma exclusão
     * lógica, você deve sobreescrever o método.
     *
     * @param id Valor do ID que representa o objeto que será excluído do
     * sistema.
     */
    @Override
    public void deleteById(K id) {
        var entity = findBy(id);
        repository.delete(entity);
    }

    @Override
    public List<E> list() {
        return repository.findAll();
    }
}
