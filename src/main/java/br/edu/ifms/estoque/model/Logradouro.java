/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

/**
 *
 * @author 1513003
 */
public class Logradouro {
    private Long id;
    private String nome;
    private TipoLogradouro tipoLogradouro;

    public Logradouro() {
    }

    public Logradouro(Long id, String nome, TipoLogradouro tipoLogradouro) {
        this.id = id;
        this.nome = nome;
        this.tipoLogradouro = tipoLogradouro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoLogradouro getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }
}
