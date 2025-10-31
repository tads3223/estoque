/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.perfil;

import br.edu.ifms.estoque.arquitetura.controller.CRUDDefaultControllerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 1513003
 */
@RestController
@RequestMapping("/api/perfil")
public class PerfilController
    extends CRUDDefaultControllerAdapter<Perfil, Long, PerfilResponse, PerfilRequest, PerfilRequest>{

    public PerfilController(
            PerfilService service, 
            PerfilMapper mapper) {
        super(service, mapper);
    }
}
