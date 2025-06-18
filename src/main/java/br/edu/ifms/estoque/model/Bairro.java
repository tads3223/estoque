/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import br.edu.ifms.estoque.model.heranca.TablePerClassBase;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;

/**
 *
 * @author 1513003
 */
@Entity
@SequenceGenerator(
        sequenceName = "bairro_sequence", 
        name = "tablePerClassBase", 
        allocationSize = 1
)
public class Bairro extends TablePerClassBase {

    public Bairro() {
    }
    
    public Bairro(Long id, String nome) {
        super(id, nome);
    }
}
