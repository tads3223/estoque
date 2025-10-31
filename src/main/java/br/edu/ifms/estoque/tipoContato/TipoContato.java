/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.tipoContato;

import br.edu.ifms.estoque.arquitetura.model.JoinedBase;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author 1513003
 */
@Data
@EqualsAndHashCode(
        callSuper = true,
        onlyExplicitlyIncluded = true
)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class TipoContato extends JoinedBase {

    @ToString.Include
    private String mascara;
}
