/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.service;

import br.edu.ifms.estoque.dto.IAgrupoProdutoDTO;
import br.edu.ifms.estoque.dto.SubgrupoProdutoRequest;
import br.edu.ifms.estoque.dto.SubgrupoProdutoResponse;
import br.edu.ifms.estoque.mapper.SubgrupoProdutoMapper;
import br.edu.ifms.estoque.model.SubgrupoProduto;
import br.edu.ifms.estoque.repository.SubgrupoProdutoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author 1513003
 */
@Service
public class SubgrupoProdutoService extends
        ServiceAdapter<SubgrupoProduto, Long, SubgrupoProdutoResponse, SubgrupoProdutoRequest, SubgrupoProdutoRequest> {

    public SubgrupoProdutoService(SubgrupoProdutoRepository repository, SubgrupoProdutoMapper mapper) {
        super(repository, mapper);
    }
    
    public List<IAgrupoProdutoDTO> listaAgrupaSubgrupo() {
        SubgrupoProdutoRepository repo = (SubgrupoProdutoRepository)super.repository;
        return repo.listaAgrupadaPorSubgrupo();
    }
    
}
