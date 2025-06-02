/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.dto;

/**
 *
 * @author 1513003
 */
public class LogradouroResponse {
    private Long id;
    private String nome;
    private String tipoLogradouro;

    public LogradouroResponse() {
    }

    public LogradouroResponse(Long id, String nome, String tipoLogradouro) {
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

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }
    
    public String getNomeCompleto() {
        return tipoLogradouro + " " + nome;
    }
    
}
