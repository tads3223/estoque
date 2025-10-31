/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.bairro;

import br.edu.ifms.estoque.arquitetura.service.ServiceAdapter;
import org.springframework.stereotype.Service;

/**
 *
 * @author 1513003
 */
@Service
public class BairroService extends 
        ServiceAdapter<Bairro, Long, BairroResponse, BairroRequest, BairroRequest> {

    public BairroService(BairroRepository repository, BairroMapper mapper) {
        super(repository, mapper);
    }
    
}
