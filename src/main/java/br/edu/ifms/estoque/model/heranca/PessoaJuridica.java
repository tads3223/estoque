/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model.heranca;

import jakarta.persistence.MappedSuperclass;

/**
 *
 * @author 1513003
 */
@MappedSuperclass
public abstract class PessoaJuridica extends Pessoa {
    
    private String razaoSocial;
    private String cnpj;

    public PessoaJuridica() {
        
    }

    public PessoaJuridica(Long id, String nome, String razaoSocial, String cnpj) {
        super(id, nome);
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
}
