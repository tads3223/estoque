/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifms.estoque.tipoContato;

import br.edu.ifms.estoque.arquitetura.repository.JpaSpecificationRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 1513003
 */
@Repository
public interface TipoContatoRepository 
        extends JpaSpecificationRepository<TipoContato, Long> {
    
}
