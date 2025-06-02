/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.mapper;

import br.edu.ifms.estoque.dto.LogradouroCreateResponse;
import br.edu.ifms.estoque.dto.LogradouroRequest;
import br.edu.ifms.estoque.dto.LogradouroResponse;
import br.edu.ifms.estoque.model.Logradouro;
import br.edu.ifms.estoque.repository.TipoLogradouroRepository;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author 1513003
 */
public class LogradouroMapper {

    public static LogradouroResponse toDto(Logradouro entity) {
        var dto = new LogradouroResponse(
                entity.getId(),
                entity.getNome(),
                entity.getTipoLogradouro().getNome());
        return dto;
    }

    public static LogradouroCreateResponse entityToDto(
            Logradouro entity
    ) {
        var tipoLogradouroResponse = TipoLogradouroMapper
                .toDto(entity.getTipoLogradouro());

        LogradouroCreateResponse dto = new LogradouroCreateResponse(
                entity.getId(),
                entity.getNome(),
                tipoLogradouroResponse
        );
        return dto;
    }

    public static List<LogradouroResponse> listDto(
            List<Logradouro> list
    ) {
        return list.stream()
                .map((entity) -> toDto(entity))
                .collect(Collectors.toList());
    }

    public static List<LogradouroResponse> listDtoDidatico(
            List<Logradouro> list
    ) {
        List<LogradouroResponse> dtoList = new LinkedList();
        for (int i = 0; i < list.size(); i++) {
            Logradouro elemento = list.get(i);
            LogradouroResponse dto = toDto(elemento);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public static Logradouro toEntity(
            LogradouroRequest dto,
            TipoLogradouroRepository repository) {
        // Primeiro, recuperar o tipo logradouro
        var tipoLogradouro = repository
                .findById(dto.getTipoLogradouroId())
                .get();
        // Depois, cria o objeto logradouro
        var logradouro = new Logradouro(
                null,
                dto.getNome(),
                tipoLogradouro);
        return logradouro;
    }
}
