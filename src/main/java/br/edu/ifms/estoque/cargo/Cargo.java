/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.cargo;

import br.edu.ifms.estoque.arquitetura.model.ElementoBase;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 *
 * @author 1513003
 */
@Entity
@DiscriminatorValue("CARGO")
public class Cargo extends ElementoBase {

    public Cargo() {
    }

    public Cargo(Long id, String nome) {
        super(id, nome);
    }
    
}
