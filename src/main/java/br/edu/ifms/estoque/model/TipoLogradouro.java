/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import br.edu.ifms.estoque.model.heranca.ElementoBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

/**
 *
 * @author 1513003
 */
@Entity
@DiscriminatorValue("TIPO_LOGRADOURO")
public class TipoLogradouro extends ElementoBase {

    private String sigla;

    @OneToMany(mappedBy = "tipoLogradouro")
    private List<Logradouro> logradouros;

    public TipoLogradouro() {
    }

    public TipoLogradouro(Long id, String nome) {
        super(id, nome);
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<Logradouro> getLogradouros() {
        return logradouros;
    }

    public void setLogradouros(List<Logradouro> logradouros) {
        this.logradouros = logradouros;
    }

}
