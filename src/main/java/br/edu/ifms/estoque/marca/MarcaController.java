/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.marca;

import br.edu.ifms.estoque.arquitetura.controller.CRUDDefaultControllerAdapter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 1513003
 */
@Tag(name = "marca", description = "Recurso de controle de marcas de produtos")
@RestController
@RequestMapping("/api/marca")
public class MarcaController extends 
        CRUDDefaultControllerAdapter<Marca, Long, MarcaResponse, MarcaRequest, MarcaRequest> {

    public MarcaController(MarcaService service, MarcaMapper mapper) {
        super(service, mapper);
    }
}
