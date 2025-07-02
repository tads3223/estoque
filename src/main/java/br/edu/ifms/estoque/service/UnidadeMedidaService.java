/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.service;

import br.edu.ifms.estoque.dto.UnidadeMedidaRequest;
import br.edu.ifms.estoque.dto.UnidadeMedidaResponse;
import br.edu.ifms.estoque.mapper.UnidadeMedidaMapper;
import br.edu.ifms.estoque.model.UnidadeMedida;
import br.edu.ifms.estoque.repository.UnidadeMedidaRepository;
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
