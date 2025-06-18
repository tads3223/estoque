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
public class TipoLogradouro extends TablePerClassBase {

    private String sigla;

    @OneToMany(mappedBy = "tipoLogradouro")
    private List<Logradouro> logradouros;

    public TipoLogradouro() {
    }

    public TipoLogradouro(Long id, String nome) {
        super(id, nome);
    }

    public TipoLogradouro(String nome, String sigla) {
        super.setNome(nome);
        this.sigla = sigla;
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
