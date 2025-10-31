/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifms.estoque.unidadeMedida;

import br.edu.ifms.estoque.arquitetura.repository.JpaSpecificationRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 1513003
 */
@Repository
public interface UnidadeMedidaRepository
        extends JpaSpecificationRepository<UnidadeMedida, Long>
{
    
}
