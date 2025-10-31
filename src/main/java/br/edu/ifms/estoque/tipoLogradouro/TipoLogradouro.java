/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.tipoLogradouro;

import br.edu.ifms.estoque.logradouro.Logradouro;
import br.edu.ifms.estoque.arquitetura.model.TablePerClassBase;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
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
public class TipoLogradouro extends TablePerClassBase {

    @ToString.Include
    private String sigla;

    @OneToMany(mappedBy = "tipoLogradouro")
    private List<Logradouro> logradouros;

}
