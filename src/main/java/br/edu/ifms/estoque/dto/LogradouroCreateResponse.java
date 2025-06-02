/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.dto;

/**
 *
 * @author 1513003
 */
public class LogradouroCreateResponse {
    private Long id;
    private String nome;
    private TipoLogradouroResponse tipoLogradouro;

    public LogradouroCreateResponse() {
    }

    public LogradouroCreateResponse(
            Long id, 
            String nome, 
            TipoLogradouroResponse tipoLogradouro) {
        this.id = id;
        this.nome = nome;
        this.tipoLogradouro = tipoLogradouro;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public TipoLogradouroResponse getTipoLogradouro() {
        return tipoLogradouro;
    }
}
