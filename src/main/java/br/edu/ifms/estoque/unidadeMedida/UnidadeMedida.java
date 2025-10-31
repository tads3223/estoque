/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.unidadeMedida;

import br.edu.ifms.estoque.arquitetura.model.JoinedBase;
import jakarta.persistence.Entity;

/**
 *
 * @author 1513003
 */
@Entity
public class UnidadeMedida extends JoinedBase {
    
    private Boolean fracionado;

    public UnidadeMedida() {
    }

    public UnidadeMedida(
            Long id, 
            String nome,
            Boolean fracionado
    ) {
        super(id, nome);
        this.fracionado = fracionado;
    }

    public Boolean getFracionado() {
        return fracionado;
    }

    public void setFracionado(Boolean fracionado) {
        this.fracionado = fracionado;
    }
    
}
