/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.service;

import br.edu.ifms.estoque.dto.BairroRequest;
import br.edu.ifms.estoque.dto.BairroResponse;
import br.edu.ifms.estoque.mapper.BairroMapper;
import br.edu.ifms.estoque.model.Bairro;
import br.edu.ifms.estoque.repository.BairroRepository;
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
