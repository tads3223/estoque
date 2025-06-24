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
public class TipoLogradouroRequest extends DefaultRequest {
   
    @NotNull(message = "O campo SIGLA não deve ser NULO")
    @NotEmpty(message = "O campo SIGLA não deve ser VAZIO")
    @NotBlank(message = "O campo SIGLA não deve ser preenchido com ESPAÇO")
    private String sigla;

    public TipoLogradouroRequest() {
    }

    public TipoLogradouroRequest(String nome, String sigla) {
        super(nome);
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }
}
