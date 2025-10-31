/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.marca;

import br.edu.ifms.estoque.arquitetura.model.ElementoBase;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author 1513003
 */
@SuperBuilder
@NoArgsConstructor

@Entity
@DiscriminatorValue("MARCA")
public class Marca extends ElementoBase {
}
