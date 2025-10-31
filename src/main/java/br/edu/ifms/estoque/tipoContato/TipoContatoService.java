/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.tipoContato;

import br.edu.ifms.estoque.arquitetura.service.ServiceAdapter;
import br.edu.ifms.estoque.tipoContato.TipoContatoRequest;
import br.edu.ifms.estoque.tipoContato.TipoContatoResponse;
import br.edu.ifms.estoque.tipoContato.TipoContatoMapper;
import br.edu.ifms.estoque.tipoContato.TipoContato;
import br.edu.ifms.estoque.tipoContato.TipoContatoRepository;
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
