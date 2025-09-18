/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.mapper;

import br.edu.ifms.estoque.dto.SubgrupoParent;
import br.edu.ifms.estoque.dto.SubgrupoProdutoRequest;
import br.edu.ifms.estoque.dto.SubgrupoProdutoResponse;
import br.edu.ifms.estoque.model.SubgrupoProduto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author 1513003
 */
@Component
public class SubgrupoProdutoMapper implements IMapper<SubgrupoProduto, SubgrupoProdutoResponse, SubgrupoProdutoRequest, SubgrupoProdutoRequest> {

    public static SubgrupoParent parentToDto(
            SubgrupoProduto entity
    ) {
        if (entity != null) {
            SubgrupoParent dto = new SubgrupoParent(
                    entity.getId(),
                    entity.getNome()
            );
            return dto;
        }
        return null;
    }

    @Override
    public SubgrupoProdutoResponse toDto(
            SubgrupoProduto entity
    ) {
        SubgrupoProdutoResponse dto = new SubgrupoProdutoResponse(
                entity.getId(),
                entity.getNome(),
                parentToDto(entity.getGrupoProduto())
        );
        return dto;
    }

    @Override
    public List<SubgrupoProdutoResponse> toListDto(
            List<SubgrupoProduto> list
    ) {
        return list.stream()
                .map((entity) -> toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public SubgrupoProduto toEntity(SubgrupoProdutoRequest request) {
        var subgrupo = SubgrupoProduto.builder().build();
        subgrupo.setNome(request.getNome());
        if (request.getGrupoProduto() != null) {
            var grupoProduto = SubgrupoProduto.builder()
                    .id(request.getGrupoProduto().getId())
                    .nome(request.getGrupoProduto().getNome())
                    .build();
            subgrupo.setGrupoProduto(grupoProduto);
        }
        return subgrupo;
    }
    
    public SubgrupoProduto toEntity(SubgrupoProdutoResponse response) {
        var subgrupo = SubgrupoProduto.builder().build();
        subgrupo.setId(response.getId());
        subgrupo.setNome(response.getNome());
        if (response.getGrupoProduto() != null) {
            var grupoProduto = SubgrupoProduto.builder()
                    .id(response.getGrupoProduto().getId())
                    .nome(response.getGrupoProduto().getNome())
                    .build();
            subgrupo.setGrupoProduto(grupoProduto);
        }
        return subgrupo;
    }

    @Override
    public SubgrupoProduto update(SubgrupoProdutoRequest request, SubgrupoProduto entity) {
        entity.setNome(request.getNome());
        if (request.getGrupoProduto() != null) {
            var grupoProduto = SubgrupoProduto.builder()
                    .id(request.getGrupoProduto().getId())
                    .nome(request.getGrupoProduto().getNome())
                    .build();
            entity.setGrupoProduto(grupoProduto);
        }
        return entity;
    }
}
