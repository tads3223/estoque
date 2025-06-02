/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.mapper;

import br.edu.ifms.estoque.dto.LogradouroResponse;
import br.edu.ifms.estoque.dto.TipoLogradouroResponse;
import static br.edu.ifms.estoque.mapper.LogradouroMapper.toDto;
import br.edu.ifms.estoque.model.Logradouro;
import br.edu.ifms.estoque.model.TipoLogradouro;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author 1513003
 */
public class TipoLogradouroMapper {
    
    public static TipoLogradouroResponse toDto(
            TipoLogradouro entity
    ) {
        TipoLogradouroResponse dto = new TipoLogradouroResponse(
                entity.getId(),
                entity.getNome()
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
}
