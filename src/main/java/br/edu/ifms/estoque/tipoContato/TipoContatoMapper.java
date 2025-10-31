/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.tipoContato;

import br.edu.ifms.estoque.arquitetura.mapper.IMapper;
import br.edu.ifms.estoque.tipoContato.TipoContatoRequest;
import br.edu.ifms.estoque.tipoContato.TipoContatoResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author 1513003
 */
@Component
public class TipoContatoMapper implements 
        IMapper<TipoContato, TipoContatoResponse, TipoContatoRequest, TipoContatoRequest> {

    @Override
    public TipoContatoResponse toDto(TipoContato entity) {
        return new TipoContatoResponse(
                entity.getId(),
                entity.getNome(),
                entity.getMascara()
        );
    }

    @Override
    public TipoContato toEntity(TipoContatoRequest request) {
        return new TipoContato(
                request.getNome(),
                request.getMascara()
        );
    }

    @Override
    public TipoContato update(TipoContatoRequest request, TipoContato entity) {
        entity.setNome(request.getNome());
        entity.setMascara(request.getMascara());
        return entity;
    }

    @Override
    public List<TipoContatoResponse> toListDto(List<TipoContato> items) {
        return items.stream()
                .map(item -> toDto(item))
                .collect(Collectors.toList());
    }
    
}
