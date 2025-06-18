/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.mapper;

import br.edu.ifms.estoque.dto.CreateTipoLogradouroRequest;
import br.edu.ifms.estoque.dto.TipoLogradouroResponse;
import br.edu.ifms.estoque.model.TipoLogradouro;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author 1513003
 */
public class TipoLogradouroMapper {
    
    public static TipoLogradouro toEntity(
            CreateTipoLogradouroRequest dto
    ) {
        var entity = new TipoLogradouro(
                dto.getNome(),
                dto.getSigla()
        );
        return entity;
    }
    
    public static TipoLogradouroResponse toDto(
            TipoLogradouro entity
    ) {
        TipoLogradouroResponse dto = new TipoLogradouroResponse(
                entity.getId(),
                entity.getNome(),
                entity.getSigla()
        );
        return dto;
    }
    
    public static List<TipoLogradouroResponse> listDto(
            List<TipoLogradouro> list
    ) {
        return list.stream()
                .map((entity) -> toDto(entity))
                .collect(Collectors.toList());
    }
    
    public static List<TipoLogradouroResponse> entitylistToDto(
            List<TipoLogradouro> list
    ) {
        List<TipoLogradouroResponse> l = new LinkedList();
        for(int i = 0; i < list.size(); i++) {
            TipoLogradouro e = list.get(i);
            TipoLogradouroResponse dto = toDto(e);
            l.add(dto);
        }
        return l;
    }
}
