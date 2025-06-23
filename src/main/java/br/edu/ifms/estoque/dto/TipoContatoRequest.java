/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.dto;

/**
 *
 * @author 1513003
 */
public class TipoContatoRequest extends DefaultRequest {
    private String mascara;

    public TipoContatoRequest() {
    }

    public TipoContatoRequest(String nome, String mascara) {
        super(nome);
        this.mascara = mascara;
    }

    public String getMascara() {
        return mascara;
    }
    
}
