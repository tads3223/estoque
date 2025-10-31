/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.marca;

import br.edu.ifms.estoque.arquitetura.dto.DefaultResponse;

/**
 *
 * @author 1513003
 */
public class MarcaResponse extends DefaultResponse {

    public MarcaResponse() {
    }

    public MarcaResponse(Long id, String nome) {
        super(id, nome);
    }
    
}
