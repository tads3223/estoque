/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.mapper;

import br.edu.ifms.estoque.dto.ProdutoCreateRequest;
import br.edu.ifms.estoque.dto.ProdutoResponse;
import br.edu.ifms.estoque.model.Produto;
import br.edu.ifms.estoque.repository.MarcaRepository;
import br.edu.ifms.estoque.repository.SubgrupoProdutoRepository;
import br.edu.ifms.estoque.repository.UnidadeMedidaRepository;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author 1513003
 */
public class ProdutoMapper {

    public static ProdutoResponse toDto(
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
            dto.setSubGrupo(SubgrupoProdutoMapper
                    .toDto(entity.getSubgrupo()));
        }
        if (entity.getUnidadeMedida() != null) {
            dto.setUnidadeMedida(UnidadeMedidaMapper
                    .toDto(entity.getUnidadeMedida()));
        }
        if (entity.getMarca() != null) {
            dto.setMarca(MarcaMapper.toDto(entity.getMarca()));
        }

        return dto;
    }

    public static List<ProdutoResponse> listDto(
            List<Produto> list
    ) {
        return list.stream()
                .map((entity) -> toDto(entity))
                .collect(Collectors.toList());
    }

    public static List<ProdutoResponse> entitylistToDto(
            List<Produto> list
    ) {
        List<ProdutoResponse> l = new LinkedList();
        for (int i = 0; i < list.size(); i++) {
            Produto e = list.get(i);
            ProdutoResponse dto = toDto(e);
            l.add(dto);
        }
        return l;
    }

    public static Produto toEntity(
            ProdutoCreateRequest dto,
            SubgrupoProdutoRepository subgrupoProdutoRepository,
            UnidadeMedidaRepository unidadeMedidaRepository,
            MarcaRepository marcaRepository
    ) {
        var entity = new Produto(null, dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setEstoqueMinimo(dto.getEstoqueMinimo());

        if (dto.getSubGrupo() != null) {
            var subgrupo = subgrupoProdutoRepository
                    .findById(dto.getSubGrupo().getId())
                    .get();
            entity.setSubgrupo(subgrupo);
        }

        if (dto.getUnidadeMedida() != null) {
            var unidadeMedida = unidadeMedidaRepository
                    .findById(dto.getUnidadeMedida().getId())
                    .get();
            entity.setUnidadeMedida(unidadeMedida);
        }

        if (dto.getMarca() != null) {
            var marca = marcaRepository
                    .findById(dto.getMarca().getId())
                    .get();
            entity.setMarca(marca);
        }
        return entity;
    }
}
