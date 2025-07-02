/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.service;

import br.edu.ifms.estoque.dto.TipoContatoRequest;
import br.edu.ifms.estoque.dto.TipoContatoResponse;
import br.edu.ifms.estoque.mapper.TipoContatoMapper;
import br.edu.ifms.estoque.model.TipoContato;
import br.edu.ifms.estoque.repository.TipoContatoRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author 1513003
 */
@Service
public class TipoContatoService 
        extends ServiceAdapter<TipoContato, Long, TipoContatoResponse, TipoContatoRequest, TipoContatoRequest> {

    public TipoContatoService(TipoContatoRepository repository,
            TipoContatoMapper mapper) {
        super(repository, mapper);
    }

}
