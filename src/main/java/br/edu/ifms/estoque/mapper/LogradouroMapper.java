/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.mapper;

import br.edu.ifms.estoque.dto.LogradouroRequest;
import br.edu.ifms.estoque.dto.LogradouroResponse;
import br.edu.ifms.estoque.exceptions.TipoLogradouroNotFoundException;
import br.edu.ifms.estoque.model.Logradouro;
import br.edu.ifms.estoque.model.TipoLogradouro;
import br.edu.ifms.estoque.repository.TipoLogradouroRepository;
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
                .findById(dto.getTipoLogradouroId())
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
        var tipoLogradouro = new TipoLogradouro(
                request.getTipoLogradouroId(), null);
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
        var tipoLogradouro = new TipoLogradouro(
                request.getTipoLogradouroId(), null);
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
                .findById(request.getTipoLogradouroId())
                .orElseThrow(TipoLogradouroNotFoundException::new);
        entity.setNome(request.getNome());
        entity.setTipoLogradouro(tipoLogradouro);
        return entity;
    }
}
