/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.unidadeMedida;

import br.edu.ifms.estoque.arquitetura.service.ServiceAdapter;
import org.springframework.stereotype.Service;

/**
 *
 * @author 1513003
 */
@Service
public class UnidadeMedidaService 
        extends ServiceAdapter<UnidadeMedida, Long, UnidadeMedidaResponse, UnidadeMedidaRequest, UnidadeMedidaRequest> {

    public UnidadeMedidaService(UnidadeMedidaRepository repository, UnidadeMedidaMapper mapper) {
        super(repository, mapper);
    }
    
}
