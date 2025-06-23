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
public abstract class DefaultRequest {
    
    @NotNull(message = "O campo NOME deve ser informado!")
    @NotEmpty(message = "O campo NOME não deve estar vazio!")
    @NotBlank(message = "O campo NOME não deve conter somente ESPAÇO VAZIO!")
    private String nome;

    public DefaultRequest() {
    }

    public DefaultRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    
}
