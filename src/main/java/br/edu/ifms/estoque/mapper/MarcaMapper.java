/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.mapper;

import br.edu.ifms.estoque.dto.MarcaResponse;
import br.edu.ifms.estoque.model.Marca;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author 1513003
 */
public class MarcaMapper {
    
    public static MarcaResponse toDto(
            Marca entity
    ) {
        MarcaResponse dto = new MarcaResponse(
                entity.getId(),
                entity.getNome()
        );
        return dto;
    }
    
    public static List<MarcaResponse> listDto(
            List<Marca> list
    ) {
        return list.stream()
                .map((entity) -> toDto(entity))
                .collect(Collectors.toList());
    }
    
    public static List<MarcaResponse> entitylistToDto(
            List<Marca> list
    ) {
        List<MarcaResponse> l = new LinkedList();
        for(int i = 0; i < list.size(); i++) {
            Marca e = list.get(i);
            MarcaResponse dto = toDto(e);
            l.add(dto);
        }
        return l;
    }
}
