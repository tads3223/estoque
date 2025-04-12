/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.uc.manter_tipo_logradouro;

import java.util.LinkedList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 1513003
 */
@RestController
public class TipoLogradouroController {
    
    private List<TipoLogradouro> lista = new LinkedList<>();
    
    @RequestMapping(method = RequestMethod.POST, name = "Criar um Tipo de Logradouro", path = "/tipo-logradouro")
    public TipoLogradouro create(
            Long id,
            String nome
    ) {
        TipoLogradouro tipoLogradouro = new TipoLogradouro(id, nome);
        lista.add(tipoLogradouro);
        return tipoLogradouro;
    }
    
    @RequestMapping(method = RequestMethod.GET, name = "Listar Tipos de Logradouros", path = "/tipo-logradouro")
    public List<TipoLogradouro> list() {
        return lista;
    }
    
}
