/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author 1513003
 */
public class BairroRequest {
    
    @NotNull(message = "O atributo NOME não deve ser NULO.")
    @NotEmpty(message = "O atributo NOME não deve ser VAZIO.")
    @NotBlank(message = "O atributo NOME não deve ter espaço VAZIO.")
    private String nome;

    public BairroRequest() {
    }

    public BairroRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    
}
