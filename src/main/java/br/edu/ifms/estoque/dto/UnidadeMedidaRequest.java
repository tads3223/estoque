/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.dto;

/**
 *
 * @author 1513003
 */
public class UnidadeMedidaRequest extends DefaultRequest {

    private Boolean fracionado;
    
    public UnidadeMedidaRequest() {
    }

    public UnidadeMedidaRequest(String nome, Boolean fracionado) {
        super(nome);
        this.fracionado = fracionado;
    }

    public Boolean getFracionado() {
        return fracionado;
    } 
}
