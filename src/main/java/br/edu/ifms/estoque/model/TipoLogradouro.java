/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import br.edu.ifms.estoque.model.heranca.TablePerClassBase;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author 1513003
 */
@Entity
@SequenceGenerator(
        sequenceName = "tipo_logradouro_sequence",
        name = "tablePerClassBase",
        allocationSize = 1
)
@Data
@EqualsAndHashCode(
        callSuper = true,
        onlyExplicitlyIncluded = true
)
@SuperBuilder
public class TipoLogradouro extends TablePerClassBase {

    private String sigla;

    @OneToMany(mappedBy = "tipoLogradouro")
    private List<Logradouro> logradouros;

}
