/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.edu.ifms.estoque.model;

import br.edu.ifms.estoque.model.heranca.TablePerClassBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author 1513003
 */
public class ProdutoTest {
    
    public ProdutoTest() {
    }

    @Test
    public void testConstrutorDinamico_retornaObjeto() {
        Long idEsperado = 1L;
        String nomeEsperado = "Banana";
        Produto p1 = Produto.builder()
                .id(idEsperado)
                .nome(nomeEsperado)
                .build();
        Assertions.assertEquals(idEsperado, p1.getId());
        Assertions.assertEquals(nomeEsperado, p1.getNome());
    }
    
    @Test
    public void testConstrutorDinamicoAlgunsAtributos_retornaObjeto() {
        Long idEsperado = 1L;
        String nomeEsperado = "Banana";
        Integer estoqueMinimo = 12;
                
        Produto p1 = Produto.builder()
                .nome(nomeEsperado)
                .estoqueMinimo(estoqueMinimo)
                .build();
        Assertions.assertNull(p1.getId());
        Assertions.assertEquals(nomeEsperado, p1.getNome());
        Assertions.assertEquals(estoqueMinimo, p1.getEstoqueMinimo());
    }
    
}
