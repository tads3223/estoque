/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifms.estoque.arquitetura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Specification Repository. Interface utilizada para se adaptar a necessidade
 * de construção de critérios de seleção para as consultas em banco de dados.
 *
 * @author nicho
 * @param <T>
 * @param <K>
 */
public interface JpaSpecificationRepository<T, K>
        extends JpaRepository<T, K>,
        JpaSpecificationExecutor<T> {

}
