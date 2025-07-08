/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.controller;

import br.edu.ifms.estoque.dto.BairroResponse;
import br.edu.ifms.estoque.dto.BairroRequest;
import br.edu.ifms.estoque.mapper.BairroMapper;
import br.edu.ifms.estoque.model.Bairro;
import br.edu.ifms.estoque.service.BairroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 1513003
 */
@Tag(name = "bairro", description = "Recurso de controle de Bairros")
@RestController
@RequestMapping("/bairro")
public class BairroController 
        extends CRUDDefaultControllerAdapter<Bairro, Long, BairroResponse, BairroRequest, BairroRequest> {

    public BairroController(BairroService service, BairroMapper mapper) {
        super(service, mapper);
    }

}
