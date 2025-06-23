/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.dto;

/**
 *
 * @author 1513003
 */
public class TipoContatoResponse extends DefaultResponse {
    
    private String mascara;

    public TipoContatoResponse() {
    }

    public TipoContatoResponse(Long id, String nome, String mascara) {
        super(id, nome);
        this.mascara = mascara;
    }

    public String getMascara() {
        return mascara;
    }
}
