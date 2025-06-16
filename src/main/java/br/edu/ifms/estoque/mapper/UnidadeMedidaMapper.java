/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.mapper;

import br.edu.ifms.estoque.dto.UnidadeMedidaResponse;
import br.edu.ifms.estoque.model.UnidadeMedida;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author 1513003
 */
public class UnidadeMedidaMapper {
    
    public static UnidadeMedidaResponse toDto(
            UnidadeMedida entity
    ) {
        UnidadeMedidaResponse dto = new UnidadeMedidaResponse(
                entity.getId(),
                entity.getNome()
        );
        return dto;
    }
    
    public static List<UnidadeMedidaResponse> listDto(
            List<UnidadeMedida> list
    ) {
        return list.stream()
                .map((entity) -> toDto(entity))
                .collect(Collectors.toList());
    }
    
    public static List<UnidadeMedidaResponse> entitylistToDto(
            List<UnidadeMedida> list
    ) {
        List<UnidadeMedidaResponse> l = new LinkedList();
        for(int i = 0; i < list.size(); i++) {
            UnidadeMedida e = list.get(i);
            UnidadeMedidaResponse dto = toDto(e);
            l.add(dto);
        }
        return l;
    }
}
