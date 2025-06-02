/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.dto;

/**
 *
 * @author 1513003
 */
public class LogradouroRequest {
    private String nome;
    private Long tipoLogradouroId;

    public LogradouroRequest() {
    }

    public LogradouroRequest(String nome, Long tipoLogradouroId) {
        this.nome = nome;
        this.tipoLogradouroId = tipoLogradouroId;
    }

    public String getNome() {
        return nome;
    }

    public Long getTipoLogradouroId() {
        return tipoLogradouroId;
    }
}
