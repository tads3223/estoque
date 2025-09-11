/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.edu.ifms.estoque.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 1513003
 */
public class TipoLogradouroTest {
    
    public TipoLogradouroTest() {
    }

    @Test
    public void testDadoNome_retornaNome() {
        Long expectedId = 1L;
        String expectedNome = "Rua";
        String expectedSiga = "R";
        TipoLogradouro tp1 = new TipoLogradouro(expectedId, expectedNome);
        tp1.setSigla(expectedSiga);
        
        Assertions.assertEquals(expectedId, tp1.getId());
        Assertions.assertEquals(expectedNome, tp1.getNome());
        Assertions.assertEquals(expectedSiga, tp1.getSigla());
    }
    
    @Test
    public void testDadoObjetoTipoLogradouro_testarEqualsTrue() {
        Long expectedId = 1L;
        String expectedNome = "Centro";
        String expectedSiga = "R";
        TipoLogradouro tp1 = new TipoLogradouro(expectedId, expectedNome);
        tp1.setSigla(expectedSiga);
        
        TipoLogradouro tp2 = new TipoLogradouro(expectedId, "Avenida");
        tp1.setSigla("AV");
        
        System.out.println(tp1);
        System.out.println(tp2);
        
        Assertions.assertTrue(tp1.equals(tp2), "Teste com tp1.equals(b2)");
        Assertions.assertEquals(tp1, tp2, "teste compara dois objetos");
        
    }
    
}
