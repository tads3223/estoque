/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import br.edu.ifms.estoque.model.heranca.JoinedBase;
import jakarta.persistence.Entity;

/**
 *
 * @author 1513003
 */
@Entity
public class Bairro extends JoinedBase {

    public Bairro() {
    }
    
    public Bairro(Long id, String nome) {
        super(id, nome);
    }
}
