/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.bairro;

import br.edu.ifms.estoque.arquitetura.controller.CRUDDefaultControllerAdapter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 1513003
 */
@Tag(name = "bairro", description = "Recurso de controle de Bairros")
@RestController
@RequestMapping("/api/bairro")
public class BairroController 
        extends CRUDDefaultControllerAdapter<Bairro, Long, BairroResponse, BairroRequest, BairroRequest> {

    public BairroController(BairroService service, BairroMapper mapper) {
        super(service, mapper);
    }

}
