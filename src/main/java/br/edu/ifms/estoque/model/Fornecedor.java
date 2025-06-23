/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import br.edu.ifms.estoque.model.heranca.Pessoa;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 *
 * @author 1513003
 */
@Entity
@DiscriminatorValue("FORNECEDOR")
public class Fornecedor extends Pessoa {
    
}
