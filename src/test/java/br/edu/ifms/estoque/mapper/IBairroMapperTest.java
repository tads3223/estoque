/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.edu.ifms.estoque.mapper;

import br.edu.ifms.estoque.bairro.BairroResponse;
import br.edu.ifms.estoque.bairro.Bairro;
import br.edu.ifms.estoque.bairro.BairroMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author 1513003
 */
public class IBairroMapperTest {
    
    public IBairroMapperTest() {
    }

    @Test
    public void testDadoEntidadeBairro_returnDTOBairro() {
        Bairro b1 = Bairro.builder()
                .id(1L)
                .nome("Centro")
                .build();
        BairroResponse bairroResponse = BairroMapper.INSTANCE
                .toDto(b1);
        Assertions.assertEquals(
                b1.getNome(), 
                bairroResponse.getNome());
    }
    
}
