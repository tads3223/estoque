/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.produto;

import br.edu.ifms.estoque.marca.MarcaMapper;
import br.edu.ifms.estoque.produto.ProdutoRequest;
import br.edu.ifms.estoque.produto.ProdutoResponse;
import br.edu.ifms.estoque.marca.MarcaNotFoundException;
import br.edu.ifms.estoque.subgrupoProduto.SubgrupoNotExistException;
import br.edu.ifms.estoque.unidadeMedida.UnidadeMedidaNotFoundException;
import br.edu.ifms.estoque.arquitetura.mapper.IMapper;
import br.edu.ifms.estoque.subgrupoProduto.SubgrupoProdutoMapper;
import br.edu.ifms.estoque.unidadeMedida.UnidadeMedidaMapper;
import br.edu.ifms.estoque.marca.MarcaRepository;
import br.edu.ifms.estoque.subgrupoProduto.SubgrupoProdutoRepository;
import br.edu.ifms.estoque.unidadeMedida.UnidadeMedidaRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author 1513003
 */
@Component
public class ProdutoMapper implements IMapper<Produto, ProdutoResponse, ProdutoRequest, ProdutoRequest> {

    private final MarcaMapper marcaMapper;
    private final SubgrupoProdutoMapper subgrupoMapper;
    private final UnidadeMedidaMapper unidadeMedidaMapper;

    public ProdutoMapper(MarcaMapper marcaMapper, SubgrupoProdutoMapper subgrupoMapper, UnidadeMedidaMapper unidadeMedidaMapper) {
        this.marcaMapper = marcaMapper;
        this.subgrupoMapper = subgrupoMapper;
        this.unidadeMedidaMapper = unidadeMedidaMapper;
    }

    @Override
    public ProdutoResponse toDto(
            Produto entity
    ) {
        ProdutoResponse dto = new ProdutoResponse(
                entity.getId(),
                entity.getNome()
        );
        dto.setDescricao(entity.getDescricao());
        dto.setEstoque(entity.getEstoque());
        dto.setEstoqueMinimo(entity.getEstoqueMinimo());
        dto.setPreco(entity.getPreco());
        dto.setDataUltimaCompra(entity.getDataUltimaCompra());

        if (entity.getSubgrupo() != null) {
            dto.setSubGrupo(subgrupoMapper
                    .toDto(entity.getSubgrupo()));
        }
        if (entity.getUnidadeMedida() != null) {
            dto.setUnidadeMedida(unidadeMedidaMapper
                    .toDto(entity.getUnidadeMedida()));
        }
        if (entity.getMarca() != null) {
            dto.setMarca(marcaMapper.toDto(entity.getMarca()));
        }

        return dto;
    }

    @Override
    public List<ProdutoResponse> toListDto(
            List<Produto> list
    ) {
        return list.stream()
                .map((entity) -> toDto(entity))
                .collect(Collectors.toList());
    }

    public Produto toEntity(
            ProdutoRequest dto,
            SubgrupoProdutoRepository subgrupoProdutoRepository,
            UnidadeMedidaRepository unidadeMedidaRepository,
            MarcaRepository marcaRepository
    ) {
        var entity = Produto.builder().nome(dto.getNome()).build();
        entity.setDescricao(dto.getDescricao());
        entity.setEstoqueMinimo(dto.getEstoqueMinimo());

        if (dto.getSubGrupo() != null) {
            var subgrupo = subgrupoProdutoRepository
                    .findById(dto.getSubGrupo().getId())
                    .orElseThrow(SubgrupoNotExistException::new);
            entity.setSubgrupo(subgrupo);
        }

        if (dto.getUnidadeMedida() != null) {
            var unidadeMedida = unidadeMedidaRepository
                    .findById(dto.getUnidadeMedida().getId())
                    .orElseThrow(UnidadeMedidaNotFoundException::new);
            entity.setUnidadeMedida(unidadeMedida);
        }

        if (dto.getMarca() != null) {
            var marca = marcaRepository
                    .findById(dto.getMarca().getId())
                    .orElseThrow(MarcaNotFoundException::new);
            entity.setMarca(marca);
        }
        return entity;
    }

    @Override
    public Produto toEntity(ProdutoRequest request) {
        var entity = Produto.builder().nome(request.getNome()).build();
        entity.setDescricao(request.getDescricao());
        entity.setEstoqueMinimo(request.getEstoqueMinimo());
        if (request.getSubGrupo() != null) {
            var subgrupo = subgrupoMapper.toEntity(request.getSubGrupo());
            entity.setSubgrupo(subgrupo);
        }

        if (request.getUnidadeMedida() != null) {
            var unidadeMedida = unidadeMedidaMapper.toEntity(request.getUnidadeMedida());
            entity.setUnidadeMedida(unidadeMedida);
        }

        if (request.getMarca() != null) {
            var marca = marcaMapper.toEntity(request.getMarca());
            entity.setMarca(marca);
        }
        return entity;
    }

    @Override
    public Produto update(ProdutoRequest request, Produto entity) {
        entity.setNome(request.getNome());
        entity.setDescricao(request.getDescricao());
        entity.setEstoqueMinimo(request.getEstoqueMinimo());
        if (request.getSubGrupo() != null) {
            var subgrupo = subgrupoMapper.toEntity(request.getSubGrupo());
            entity.setSubgrupo(subgrupo);
        }

        if (request.getUnidadeMedida() != null) {
            var unidadeMedida = unidadeMedidaMapper.toEntity(request.getUnidadeMedida());
            entity.setUnidadeMedida(unidadeMedida);
        }

        if (request.getMarca() != null) {
            var marca = marcaMapper.toEntity(request.getMarca());
            entity.setMarca(marca);
        }
        return entity;
    }
}
