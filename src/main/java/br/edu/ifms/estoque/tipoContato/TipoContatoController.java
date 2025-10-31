/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.tipoContato;

import br.edu.ifms.estoque.arquitetura.controller.CRUDDefaultControllerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 1513003
 */
@RestController
@RequestMapping("/api/tipo-contato")
public class TipoContatoController extends
        CRUDDefaultControllerAdapter<TipoContato, Long, TipoContatoResponse, TipoContatoRequest, TipoContatoRequest> {

    public TipoContatoController(
            TipoContatoService service,
            TipoContatoMapper mapper) {
        super(service, mapper);
    }
}
