/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.dto;

import jakarta.validation.constraints.NotNull;

/**
 *
 * @author 1513003
 */
public class LogradouroRequest extends DefaultRequest {
    
    @NotNull(message = "O campo TIPO DE LOGRADOURO não foi informado.")
    private TipoLogradouroResponse tipoLogradouro;

    public LogradouroRequest() {
    }

    public LogradouroRequest(String nome, TipoLogradouroResponse tipoLogradouro) {
        super(nome);
        this.tipoLogradouro = tipoLogradouro;
    }

    public TipoLogradouroResponse getTipoLogradouro() {
        return tipoLogradouro;
    }
}
