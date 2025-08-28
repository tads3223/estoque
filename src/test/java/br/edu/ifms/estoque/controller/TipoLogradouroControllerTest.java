/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.edu.ifms.estoque.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
/**
 *
 * @author 1513003
 */
@SpringBootTest
@AutoConfigureMockMvc
public class TipoLogradouroControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void dadoTipoLogradouro_retorneObjeto() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/tipo-logradouro/{id}", 1L);
        ResultActions result = mockMvc.perform(request);
        // Verificar se o STATUS da requisição é igual a 200.
        ResultMatcher resultStatus = MockMvcResultMatchers.status().isOk();
        result.andExpect(resultStatus);
        // Verifica se o tipo do conteúdo é JSON
        ResultMatcher resultMediaType = MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON);
        result.andExpect(resultMediaType);
        
        // verificar o ID
        Long expectedId = 1L;
        ResultMatcher resultId = MockMvcResultMatchers.jsonPath("$.id").value(expectedId);
        result.andExpect(resultId);
    }
    
    @Test
    public void dadoJson_retornarCriado() throws Exception {
        String jsonRequest = """
                             {
                                "nome": "Travessa",
                                "sigla": "TR"
                             }
                             """;
        RequestBuilder request = MockMvcRequestBuilders
                .post("/tipo-logradouro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest);
        ResultActions result = mockMvc.perform(request);
        // Verificar se o STATUS da requisição é igual a 201.
        ResultMatcher statusExpected = MockMvcResultMatchers.status().isCreated();
        result.andExpect(statusExpected);
        
        // verificar o ID
        Long expectedId = -46L;
        ResultMatcher resultId = MockMvcResultMatchers.jsonPath("$.id").value(expectedId);
        result.andExpect(resultId);
    }
    
}
