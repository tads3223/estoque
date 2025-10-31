/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.produto;

import br.edu.ifms.estoque.arquitetura.mapper.IMapper;
import br.edu.ifms.estoque.marca.MarcaMapper;
import br.edu.ifms.estoque.subgrupoProduto.SubgrupoProdutoMapper;
import br.edu.ifms.estoque.unidadeMedida.UnidadeMedidaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author 1513003
 */
@Mapper(
        uses = {
            MarcaMapper.class,
            UnidadeMedidaMapper.class,
            SubgrupoProdutoMapper.class
        }
)
public interface ProdutoMapper extends
        IMapper<Produto, ProdutoResponse, ProdutoRequest, ProdutoRequest> {

    public ProdutoMapper INSTANCE = Mappers
            .getMapper(ProdutoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "preco", ignore = true)
    @Mapping(target = "estoque", ignore = true)
    @Override
    public Produto toEntity(ProdutoRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "preco", ignore = true)
    @Mapping(target = "estoque", ignore = true)
    @Override
    public Produto update(
            ProdutoRequest request,
            @MappingTarget Produto entity);

}
