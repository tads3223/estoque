/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.edu.ifms.estoque.model;

import br.edu.ifms.estoque.compra.PedidoCompra;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author 1513003
 */
public class PedidoCompraTest {
    
    @Test
    public void testarContrutorComIdEData() {
        Long id = 1L;
        LocalDateTime emissao = LocalDateTime.now();
        PedidoCompra pc = new PedidoCompra(id, emissao);
        Assertions.assertEquals(id, pc.getId(), "Teste do ID da classe");
        Assertions.assertEquals(emissao, pc.getEmissao(), "Teste da data de emissao");
    }
    
    @Test
    public void testarConstrutorComIdEDataSemDados() {
        Long id = 1L;
        LocalDateTime emissao = LocalDateTime.now();
        PedidoCompra pc = new PedidoCompra(id, emissao);
        Assertions.assertNull(pc.getId());
        Assertions.assertNull(pc.getEmissao());
    }
    
}
