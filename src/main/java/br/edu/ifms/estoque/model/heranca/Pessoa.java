/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model.heranca;

import br.edu.ifms.estoque.enumeration.PersonType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import java.io.Serializable;

/**
 *
 * @author 1513003
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "classe_pessoa")
public abstract class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String nomeSocial;
    private PersonType tipoPessoa;

    public Pessoa() {
    }

    public Pessoa(Long id, String nome, String nomeSocial, PersonType tipoPessoa) {
        this.id = id;
        this.nome = nome;
        this.nomeSocial = nomeSocial;
    }

    public Pessoa(String nome, String nomeSocial, PersonType tipoPessoa) {
        this.nome = nome;
        this.nomeSocial = nomeSocial;
        this.tipoPessoa = tipoPessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public PersonType getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(PersonType tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

}
