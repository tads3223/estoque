/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.marca;

import br.edu.ifms.estoque.arquitetura.service.ServiceAdapter;
import org.springframework.stereotype.Service;

/**
 *
 * @author 1513003
 */
@Service
public class MarcaService extends
        ServiceAdapter<Marca, Long, MarcaResponse, MarcaRequest, MarcaRequest> {

    public MarcaService(MarcaRepository repository, MarcaMapper mapper) {
        super(repository, mapper);
    }

}
