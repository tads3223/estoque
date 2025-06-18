/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import br.edu.ifms.estoque.model.heranca.JoinedBase;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

/**
 *
 * @author 1513003
 */
@Entity
public class Logradouro extends JoinedBase {
    
    @ManyToOne
    private TipoLogradouro tipoLogradouro;

    public Logradouro() {
    }

    public Logradouro(Long id, String nome, TipoLogradouro tipoLogradouro) {
        super(id, nome);
        this.tipoLogradouro = tipoLogradouro;
    }

    public TipoLogradouro getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }
}
