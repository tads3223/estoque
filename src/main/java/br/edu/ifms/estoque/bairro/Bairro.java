/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.bairro;

import br.edu.ifms.estoque.arquitetura.model.TablePerClassBase;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import lombok.experimental.SuperBuilder;

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
@SuperBuilder
public class Bairro extends TablePerClassBase {

}
