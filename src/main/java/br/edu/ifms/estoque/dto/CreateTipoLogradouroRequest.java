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
public class CreateTipoLogradouroRequest {
    @NotEmpty
    @NotBlank
    private String nome;
    
    @NotNull(message = "O campo SIGLA não deve ser NULO")
    @NotEmpty(message = "O campo SIGLA não deve ser VAZIO")
    @NotBlank(message = "O campo SIGLA não deve ser preenchido com ESPAÇO")
    private String sigla;

    public CreateTipoLogradouroRequest() {
    }

    public CreateTipoLogradouroRequest(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }
}
