/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.tipoLogradouro;

import br.edu.ifms.estoque.arquitetura.dto.DefaultResponse;

/**
 *
 * @author 1513003
 */
public class TipoLogradouroResponse extends
        DefaultResponse {

    private String sigla;

    public TipoLogradouroResponse() {
    }

    public TipoLogradouroResponse(
            Long id, 
            String nome, 
            String sigla
    ) {
        super(id, nome);
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }
}
