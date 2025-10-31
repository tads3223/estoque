/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.tipoLogradouro;

import br.edu.ifms.estoque.arquitetura.service.ServiceAdapter;
import br.edu.ifms.estoque.tipoLogradouro.TipoLogradouroRequest;
import br.edu.ifms.estoque.tipoLogradouro.TipoLogradouroResponse;
import br.edu.ifms.estoque.tipoLogradouro.TipoLogradouroMapper;
import br.edu.ifms.estoque.tipoLogradouro.TipoLogradouro;
import br.edu.ifms.estoque.tipoLogradouro.TipoLogradouroRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author 1513003
 */
@Service
public class TipoLogradouroService extends
        ServiceAdapter<
            TipoLogradouro,
            Long,
            TipoLogradouroResponse,
            TipoLogradouroRequest,
            TipoLogradouroRequest
        >{

    public TipoLogradouroService(
            TipoLogradouroRepository repository, 
            TipoLogradouroMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public List<TipoLogradouro> list() {
        TipoLogradouroRepository repo = (TipoLogradouroRepository) super.repository;
        return repo.listarTiposDeLogradouros();
    }
    
    public List<TipoLogradouro> listarPorNome(String nome) {
        TipoLogradouroRepository repo = (TipoLogradouroRepository) super.repository;
        return repo.listarPorNome(nome);
    }

    @Override
    public TipoLogradouro update(Long id, TipoLogradouroRequest request) {
        TipoLogradouroRepository repo = (TipoLogradouroRepository) super.repository;
        repo.atualizar(id, request.getNome(), request.getSigla());
        return mapper.toEntity(request);
    }
}
