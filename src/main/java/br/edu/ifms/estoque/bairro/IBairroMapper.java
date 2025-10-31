/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifms.estoque.bairro;

import br.edu.ifms.estoque.bairro.BairroResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author 1513003
 */
@Mapper
public interface IBairroMapper {
    
    public IBairroMapper INSTANCE = Mappers
            .getMapper(IBairroMapper.class);
    
    public BairroResponse toDto(Bairro entity);
    
}
