/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.perfil;

import br.edu.ifms.estoque.arquitetura.service.ServiceAdapter;
import org.springframework.stereotype.Service;

/**
 *
 * @author 1513003
 */
@Service
public class PerfilService extends 
        ServiceAdapter<Perfil, Long, PerfilResponse, PerfilRequest, PerfilRequest>{

    public PerfilService(
            PerfilRepository repository, 
            PerfilMapper mapper) {
        super(repository, mapper);
    }

}
