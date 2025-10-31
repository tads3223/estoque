/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.tipoLogradouro;

import br.edu.ifms.estoque.arquitetura.service.ServiceAdapter;
import org.springframework.stereotype.Service;

/**
 *
 * @author 1513003
 */
@Service
public class TipoLogradouroService extends
        ServiceAdapter<
            TipoLogradouro,
            Long,
            TipoLogradouroResponse,
            TipoLogradouroRequest,
            TipoLogradouroRequest
        >{

    public TipoLogradouroService(
            TipoLogradouroRepository repository, 
            TipoLogradouroMapper mapper) {
        super(repository, mapper);
    }
}
