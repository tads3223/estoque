/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.mapper;

import br.edu.ifms.estoque.dto.SubgrupoParent;
import br.edu.ifms.estoque.dto.SubgrupoProdutoResponse;
import br.edu.ifms.estoque.model.SubgrupoProduto;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author 1513003
 */
public class SubgrupoProdutoMapper {

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

    public static SubgrupoProdutoResponse toDto(
            SubgrupoProduto entity
    ) {
        SubgrupoProdutoResponse dto = new SubgrupoProdutoResponse(
                entity.getId(),
                entity.getNome(),
                parentToDto(entity.getGrupoProduto())
        );
        return dto;
    }

    public static List<SubgrupoProdutoResponse> listDto(
            List<SubgrupoProduto> list
    ) {
        return list.stream()
                .map((entity) -> toDto(entity))
                .collect(Collectors.toList());
    }

    public static List<SubgrupoProdutoResponse> entitylistToDto(
            List<SubgrupoProduto> list
    ) {
        List<SubgrupoProdutoResponse> l = new LinkedList();
        for (int i = 0; i < list.size(); i++) {
            SubgrupoProduto e = list.get(i);
            SubgrupoProdutoResponse dto = toDto(e);
            l.add(dto);
        }
        return l;
    }
}
