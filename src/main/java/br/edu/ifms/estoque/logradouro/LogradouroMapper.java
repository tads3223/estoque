/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.logradouro;

import br.edu.ifms.estoque.tipoLogradouro.TipoLogradouroNotFoundException;
import br.edu.ifms.estoque.arquitetura.mapper.IMapper;
import br.edu.ifms.estoque.tipoLogradouro.TipoLogradouroMapper;
import br.edu.ifms.estoque.tipoLogradouro.TipoLogradouro;
import br.edu.ifms.estoque.tipoLogradouro.TipoLogradouroRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author 1513003
 */
@Component
public class LogradouroMapper implements IMapper<Logradouro, LogradouroResponse, LogradouroRequest, LogradouroRequest> {

    private final TipoLogradouroMapper tipoLogradouroMapper;

    public LogradouroMapper(TipoLogradouroMapper tipoLogradouroMapper) {
        this.tipoLogradouroMapper = tipoLogradouroMapper;
    }

    @Override
    public LogradouroResponse toDto(Logradouro entity) {
        var dto = new LogradouroResponse(
                entity.getId(),
                entity.getNome(),
                tipoLogradouroMapper.toDto(entity.getTipoLogradouro()));
        return dto;
    }

    @Override
    public List<LogradouroResponse> toListDto(
            List<Logradouro> items
    ) {
        return items.stream()
                .map((entity) -> toDto(entity))
                .collect(Collectors.toList());
    }

    public Logradouro toEntity(
            LogradouroRequest dto,
            TipoLogradouroRepository repository) {
        // Primeiro, recuperar o tipo logradouro
        var tipoLogradouro = repository
                .findById(dto.getTipoLogradouro().getId())
                .orElseThrow(TipoLogradouroNotFoundException::new);
        // Depois, cria o objeto logradouro
        var logradouro = new Logradouro(
                null,
                dto.getNome(),
                tipoLogradouro);
        return logradouro;
    }

    @Override
    public Logradouro toEntity(LogradouroRequest request) {
        // Primeiro, cria o tipo de logradouro
        var tipoLogradouro = TipoLogradouro.builder()
                .nome(request.getNome())
                .build();
        // Depois, cria o objeto logradouro
        var logradouro = new Logradouro(
                null,
                request.getNome(),
                tipoLogradouro);
        return logradouro;
    }

    @Override
    public Logradouro update(LogradouroRequest request, Logradouro entity) {
        // Primeiro, cria o tipo de logradouro
        var tipoLogradouro = TipoLogradouro.builder()
                .id(request.getTipoLogradouro().getId())
                .build();
        entity.setNome(request.getNome());
        entity.setTipoLogradouro(tipoLogradouro);
        return entity;
    }

    public Logradouro update(
            LogradouroRequest request,
            Logradouro entity,
            TipoLogradouroRepository repository) {
        // Primeiro, recuperar o tipo logradouro
        var tipoLogradouro = repository
                .findById(request.getTipoLogradouro().getId())
                .orElseThrow(TipoLogradouroNotFoundException::new);
        entity.setNome(request.getNome());
        entity.setTipoLogradouro(tipoLogradouro);
        return entity;
    }
}
