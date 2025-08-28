/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.edu.ifms.estoque.repository;

import br.edu.ifms.estoque.model.SubgrupoProduto;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 *
 * @author 1513003
 */
@DataJpaTest
public class SubgrupoProdutoRepositoryTest {
    
    @Autowired
    private SubgrupoProdutoRepository repository;
    
    @Test
    public void testarBuscarPorGruposdeProdutos() {
        int quantidadeEsperada = 4;
        List<Long> items = Arrays.asList(1L);
        Assertions.assertEquals(1, items.size(), "Quantidade de itens de busca");
        List<SubgrupoProduto> itensSubgrupo = repository.buscarPorGruposdeProdutos(items);
        Assertions.assertEquals(quantidadeEsperada, itensSubgrupo.size(), "Quantidade de elementos retornados");
        
    }
    
}
