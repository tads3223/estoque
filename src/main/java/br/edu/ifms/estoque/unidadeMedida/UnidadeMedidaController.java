/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.unidadeMedida;

import br.edu.ifms.estoque.arquitetura.controller.CRUDDefaultControllerAdapter;
import br.edu.ifms.estoque.arquitetura.mapper.IMapper;
import br.edu.ifms.estoque.arquitetura.service.IService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 1513003
 */
@Tag(name = "unidade_medida", description = "Recurso de controle de Unidades de Medidas")
@RestController
@RequestMapping("/api/unidade-medida")
public class UnidadeMedidaController 
        extends CRUDDefaultControllerAdapter<UnidadeMedida, Long, UnidadeMedidaResponse, UnidadeMedidaRequest, UnidadeMedidaRequest> {

    public UnidadeMedidaController(IService<UnidadeMedida, Long, UnidadeMedidaResponse, UnidadeMedidaRequest, UnidadeMedidaRequest> service, IMapper<UnidadeMedida, UnidadeMedidaResponse, UnidadeMedidaRequest, UnidadeMedidaRequest> mapper) {
        super(service, mapper);
    }

}
