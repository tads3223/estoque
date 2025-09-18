/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.edu.ifms.estoque.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author 1513003
 */
public class BairroTest {
    
    public BairroTest() {
    }

    @Test
    public void testDadoNome_retornaNome() {
        Long expectedId = 1L;
        String expectedNome = "Centro";
        Bairro b1 = Bairro.builder().id(expectedId).nome(expectedNome).build();
        Assertions.assertEquals(expectedId, b1.getId());
        Assertions.assertEquals(expectedNome, b1.getNome());
    }
    
    @Test
    public void testDadoObjetoBairro_testarEqualsTrue() {
        Long expectedId = 1L;
        String expectedNome = "Centro";
        Bairro b1 = Bairro.builder().id(expectedId).nome(expectedNome).build();
        Bairro b2 = Bairro.builder().id(expectedId).nome("Paraíso I").build();
        
        System.out.println(b1);
        System.out.println(b2);
        
        Assertions.assertTrue(b1.equals(b2), "Teste com b1.equals(b2)");
        Assertions.assertEquals(b1, b2, "teste compara dois objetos");
        
    }
    
}
