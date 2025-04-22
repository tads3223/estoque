/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.controller;

import br.edu.ifms.estoque.model.TipoLogradouro;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
            @RequestParam(name = "id", required = true) Long id,
            @RequestParam(name = "nome", required = true) String nome
    ) {
        TipoLogradouro tipoLogradouro = new TipoLogradouro(id, nome);
        lista.add(tipoLogradouro);
        return tipoLogradouro;
    }

    @RequestMapping(method = RequestMethod.GET, name = "Listar Tipos de Logradouros", path = "/tipo-logradouro")
    public List<TipoLogradouro> list() {
        return lista;
    }

    /**
     * Registro da busca em uma lista ao invés de usar o STREAM.
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/tipo-logradouro/{id}")
    public TipoLogradouro findBy(
            @PathVariable Long id
    ) {
        var optional = find(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public Optional<TipoLogradouro> find(
            Long id
    ) {
        Optional<TipoLogradouro> optional = lista.stream()
                .filter((item) -> item.getId().equals(id))
                .findAny();
        return optional;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/tipo-logradouro/{id}")
    public void delete(
            @PathVariable(name = "id") Long id
    ) {
        var optional = find(id);
        if (optional.isPresent()) {
            TipoLogradouro tipoLogradouro = optional.get();
            lista.remove(tipoLogradouro);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/tipo-logradouro/{id}")
    public TipoLogradouro update(
            @PathVariable Long id,
            @RequestParam(name = "nome", required = true) String nome
    ) {
        var optional = find(id);
        if (optional.isPresent()) {
            var tipoLogradouro = optional.get();
            tipoLogradouro.setNome(nome);
            return tipoLogradouro;
        }
        return null;
    }

}
